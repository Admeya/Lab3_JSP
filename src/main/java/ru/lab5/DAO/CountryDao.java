package ru.lab5.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.lab5.Entities.CountryEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CountryDao extends AbstractDao<CountryEntity> {

    @Override
    public String getTableName() {
        return CountryEntity.tableName;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + CountryEntity.tableName + " (" + CountryEntity.columnName + ") " +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + CountryEntity.tableName + " SET " + CountryEntity.columnName + " = ? WHERE " +
                CountryEntity.columnId + " = ?";
    }

    @Override
    public List<CountryEntity> parseResultSet(ResultSet rs) {
        LinkedList<CountryEntity> result = new LinkedList<CountryEntity>();
        try {
            while (rs.next()) {
                CountryEntity country = new CountryEntity(rs.getInt(CountryEntity.columnId), rs.getString(CountryEntity.columnName).trim());
                result.add(country);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + CountryEntity.tableName + " в объект CountryDAO", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CountryEntity object) {
        try {
            statement.setString(1, object.getNameCountry());
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + CountryEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CountryEntity object) {
        try {
            statement.setString(1, object.getNameCountry());
            statement.setInt(2, object.getIdCountry());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + CountryEntity.tableName, e);
        }
    }
}
