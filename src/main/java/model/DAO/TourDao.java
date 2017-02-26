package model.DAO;

import Entities.TourEntity;
import common.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


public class TourDao extends AbstractDao<TourEntity> {

    public TourDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return TourEntity.tableName;
    }

    @Override
    public File getXMLPath() {
        return new File("./Tour.xml");
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
                tour.setDateStart(Utils.sqlDateToUtilDate(rs.getDate(TourEntity.columnDateStart)));
                tour.setDateEnd(Utils.sqlDateToUtilDate(rs.getDate(TourEntity.columnDateEnd)));
                tour.setCost(rs.getInt(TourEntity.columnCost));
                tour.setIdDestination(rs.getInt(TourEntity.columnIdDestination));
                result.add(tour);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + TourEntity.tableName + " в объект TourDAO:", e);
        } catch (ParseException e) {
            logger.error("Parse Exception при преобразовании дат из таблицы " + TourEntity.tableName + " в объект TourDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TourEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setDate(2, Utils.utilDateToSQLDate(object.getDateStart()));
            statement.setDate(3, Utils.utilDateToSQLDate(object.getDateEnd()));
            statement.setInt(4, object.getCost());
            statement.setInt(5, object.getDestinationPlaceByIdDestination().getIdDestination());
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + TourEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TourEntity object) {

    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public void delete(TourEntity object) {

    }
}
