package ru.lab5.DAO;

import org.springframework.stereotype.Component;
import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.DestinationEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class DestinationDao extends AbstractDao<DestinationEntity> {

    public static final String GET_ALL_DESTINATION = "SELECT d." + DestinationEntity.columnId + ", d." + DestinationEntity.columnIdCountry + ", d." +
            DestinationEntity.columnResort + ", d." + DestinationEntity.columnHotel + ", c." + CountryEntity.columnName +
            " FROM " + DestinationEntity.tableName + " d left join " + CountryEntity.tableName + " c " +
            "on d." + DestinationEntity.columnIdCountry + " = c." + CountryEntity.columnId;

    @Override
    public String getTableName() {
        return DestinationEntity.tableName;
    }

    @Override
    public String getSelectAllQuery() {
        return GET_ALL_DESTINATION;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + DestinationEntity.tableName + " (" + DestinationEntity.columnIdCountry + "," + DestinationEntity.columnResort + "," +
                DestinationEntity.columnHotel + ") " +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + DestinationEntity.tableName + " SET " + DestinationEntity.columnIdCountry + " = ? ," + DestinationEntity.columnResort + " = ?," +
                DestinationEntity.columnHotel + " = ? WHERE " +
                DestinationEntity.columnId + " = ?";
    }

    @Override
    public List<DestinationEntity> parseResultSet(ResultSet rs) {
        LinkedList<DestinationEntity> result = new LinkedList<DestinationEntity>();
        try {
            while (rs.next()) {
                DestinationEntity dest = new DestinationEntity();
                dest.setIdDestination(rs.getInt(DestinationEntity.columnId));
                dest.setIdCountry(rs.getInt(DestinationEntity.columnIdCountry));
                dest.setResort(rs.getString(DestinationEntity.columnResort).trim());
                dest.setHotel(rs.getString(DestinationEntity.columnHotel).trim());
                dest.setNameCountry(rs.getString(CountryEntity.columnName));
                result.add(dest);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + DestinationEntity.tableName + " в объект ClientDestinationDAO", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, DestinationEntity object) {
        try {
            statement.setInt(1, object.getIdCountry());
            statement.setString(2, object.getResort());
            statement.setString(3, object.getHotel());
        } catch (SQLException e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + DestinationEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, DestinationEntity object) {
        try {
            statement.setInt(1, object.getIdCountry());
            statement.setString(2, object.getResort());
            statement.setString(3, object.getHotel());
            statement.setInt(4, object.getIdDestination());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + DestinationEntity.tableName, e);
        }
    }
}
