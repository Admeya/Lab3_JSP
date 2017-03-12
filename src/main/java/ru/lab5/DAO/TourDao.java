package ru.lab5.DAO;

import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.DestinationEntity;
import ru.lab5.Entities.TourEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
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
    public String getSelectAllQuery() {
        return GET_ALL_TOURS;
    }

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
        return "UPDATE " + TourEntity.tableName + " SET " + TourEntity.columnName + " = ? ," + TourEntity.columnDateStart + " = ?," +
                TourEntity.columnDateEnd + " = ?," + TourEntity.columnCost + " = ?," + TourEntity.columnIdDestination + " = ? WHERE " +
                TourEntity.columnId + " = ?";
    }

    @Override
    public List<TourEntity> parseResultSet(ResultSet rs) {
        LinkedList<TourEntity> result = new LinkedList<TourEntity>();
        try {
            while (rs.next()) {
                TourEntity tour = new TourEntity();
                tour.setIdTour(rs.getInt(TourEntity.columnId));
                tour.setName(rs.getString(TourEntity.columnName).trim());
                tour.setDateStart(rs.getDate(TourEntity.columnDateStart));
                tour.setDateEnd(rs.getDate(TourEntity.columnDateEnd));
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
            statement.setDate(2, object.getDateStart());
            statement.setDate(3, object.getDateEnd());
            statement.setInt(4, object.getCost());
            statement.setInt(5, object.getIdDestination());
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + TourEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TourEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, object.getDateStart());
            statement.setDate(3, object.getDateEnd());
            statement.setInt(4, object.getCost());
            statement.setInt(5, object.getIdDestination());
            statement.setInt(6, object.getIdTour());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + TourEntity.tableName, e);
        }
    }
}
