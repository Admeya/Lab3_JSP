package ru.lab5.DAO;

import ru.lab5.Entities.DestinationEntity;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class DestinationDao extends AbstractDao<DestinationEntity> {

    @Override
    public String getTableName() {
        return DestinationEntity.tableName;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + DestinationEntity.tableName + " (" + DestinationEntity.columnIdCountry + "," + DestinationEntity.columnResort + "," +
                DestinationEntity.columnHotel + ") " +
                "VALUES (?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return null;
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
            statement.setInt(1, object.getCountryByIdCountry().getIdCountry());
            statement.setString(2, object.getResort());
            statement.setString(3, object.getHotel());
        } catch (SQLException e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + DestinationEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, DestinationEntity object) {

    }
}
