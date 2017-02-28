package model.DAO;

import Entities.CountryEntity;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class CountryDao extends AbstractDao<CountryEntity> {

    public CountryDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return CountryEntity.tableName;
    }

    @Override
    public File getXMLPath() {
        return new File("./Country.xml");
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + CountryEntity.tableName + " (" + CountryEntity.columnName + ") " +
                "VALUES (?);";
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public List<CountryEntity> parseResultSet(ResultSet rs) {
        LinkedList<CountryEntity> result = new LinkedList<CountryEntity>();
        try {
            while (rs.next()) {
                CountryEntity country = new CountryEntity();
                country.setIdCountry(rs.getInt(CountryEntity.columnId));
                country.setNameCountry(rs.getString(CountryEntity.columnName).trim());
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

    }
}
