package ru.lab3.DAO;

import ru.lab3.Entities.CountryEntity;
import ru.lab3.Entities.DestinationEntity;
import ru.lab3.Entities.TourEntity;
import ru.lab3.common.Utils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class TourDao extends AbstractDao<TourEntity> {

    public static final String GET_ALL_TOURS = "SELECT t." + TourEntity.columnId + ", t." + TourEntity.columnName + ", t." +
            TourEntity.columnDateStart + ", t." + TourEntity.columnDateEnd + ", t." + TourEntity.columnCost + ", c." + CountryEntity.columnId +
            ", c." + CountryEntity.columnName + ", d." + DestinationEntity.columnId + ", d." + DestinationEntity.columnResort + ", d." +
            DestinationEntity.columnHotel + " FROM " + TourEntity.tableName + " t left join " + DestinationEntity.tableName + " d " +
            "on t." + TourEntity.columnIdDestination + " = d." + DestinationEntity.columnId + " left join " + CountryEntity.tableName +
            " c on d." + DestinationEntity.columnIdCountry + " = c." + CountryEntity.columnId;

    @Override
    public String getTableName() {
        return TourEntity.tableName;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (" + TourEntity.columnName + "," + TourEntity.columnDateStart + "," +
                TourEntity.columnDateEnd + "," + TourEntity.columnCost + "," + TourEntity.columnIdDestination + ") " +
                "VALUES (?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public List<TourEntity> parseResultSet(ResultSet rs) {
        LinkedList<TourEntity> result = new LinkedList<TourEntity>();
        try {
            while (rs.next()) {
                TourEntity tour = new TourEntity();
                tour.setIdTour(rs.getInt(TourEntity.columnId));
                tour.setName(rs.getString(TourEntity.columnName).trim());
                tour.setDateStart(rs.getDate(TourEntity.columnDateStart).toLocalDate());
                tour.setDateEnd(rs.getDate(TourEntity.columnDateEnd).toLocalDate());
                tour.setCost(rs.getInt(TourEntity.columnCost));
                tour.setIdDestination(rs.getInt(TourEntity.columnIdDestination));
                tour.setIdCountry(rs.getInt(CountryEntity.columnId));
                tour.setNameCountry(rs.getString(CountryEntity.columnName));
                tour.setResort(rs.getString(DestinationEntity.columnResort));
                tour.setHotel(rs.getString(DestinationEntity.columnHotel));
                result.add(tour);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + TourEntity.tableName + " в объект TourDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TourEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, java.sql.Date.valueOf(object.getDateStart()));
            statement.setDate(3, java.sql.Date.valueOf(object.getDateEnd()));
            statement.setInt(4, object.getCost());
            statement.setInt(5, object.getDestinationPlaceByIdDestination().getIdDestination());
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + TourEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TourEntity object) {

    }

    public List<TourEntity> selectAllTours() {
        List<TourEntity> list = new ArrayList<TourEntity>();
        ResultSet rs = null;
        String sql = GET_ALL_TOURS;

        try (Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            rs = statement.executeQuery(sql);
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("Возникла ошибка при извлечении данных из БД: " + sql, e);
        }
        return list;
    }
}
