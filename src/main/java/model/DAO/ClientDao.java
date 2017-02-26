package model.DAO;

import Entities.ClientEntity;
import common.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


public class ClientDao extends AbstractDao<ClientEntity> {

    public ClientDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return ClientEntity.tableName;
    }

    @Override
    public File getXMLPath() {
        return new File("./Client.xml");
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + ClientEntity.tableName + " (" + ClientEntity.columnName + "," + ClientEntity.columnSurname + "," +
                ClientEntity.columnMiddlename + "," + ClientEntity.columnBirthdate + "," + ClientEntity.columnPasport + "," +
                ClientEntity.columnPhone + ", " + ClientEntity.columnLogin + ", " + ClientEntity.columnPass + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + ClientEntity.tableName + " SET (" + ClientEntity.columnName + " = ? ," + ClientEntity.columnSurname + " = ?," +
                ClientEntity.columnMiddlename + " = ?," + ClientEntity.columnBirthdate + " = ?," + ClientEntity.columnPasport + " = ?," +
                ClientEntity.columnPhone + " = ?, " + ClientEntity.columnLogin + " = ?, " + ClientEntity.columnPass + " = ?) WHERE " +
                ClientEntity.columnIdClient + " = ?";
    }

    @Override
    public List<ClientEntity> parseResultSet(ResultSet rs) {
        LinkedList<ClientEntity> result = new LinkedList<ClientEntity>();
        try {
            if (!rs.next()) {
                return null;
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    ClientEntity client = new ClientEntity();
                    client.setIdClient(rs.getInt(ClientEntity.columnIdClient));
                    client.setName(rs.getString(ClientEntity.columnName).trim());
                    client.setSurname(rs.getString(ClientEntity.columnSurname).trim());
                    client.setMiddlename(rs.getString(ClientEntity.columnMiddlename).trim());
                    client.setBirthdate(Utils.sqlDateToUtilDate(rs.getDate(ClientEntity.columnBirthdate)));
                    client.setPassportSerNum(rs.getString(ClientEntity.columnPasport).trim());
                    client.setPhone(rs.getString(ClientEntity.columnPhone).trim());
                    client.setLogin(rs.getString(ClientEntity.columnLogin).trim());
                    client.setPass(rs.getString(ClientEntity.columnPass).trim());
                    result.add(client);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + ClientEntity.tableName + " в объект ClientDAO:", e);
        } catch (ParseException e) {
            logger.error("Parse Exception при преобразовании дат из таблицы " + ClientEntity.tableName + " в объект ClientDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ClientEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getMiddlename());
            statement.setDate(4, Utils.utilDateToSQLDate(object.getBirthdate()));
            statement.setString(5, object.getPassportSerNum());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getLogin());
            statement.setString(8, object.getPass());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ClientEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getMiddlename());
            statement.setDate(4, Utils.utilDateToSQLDate(object.getBirthdate()));
            statement.setString(5, object.getPassportSerNum());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getLogin());
            statement.setString(8, object.getPass());
            statement.setInt(8, object.getIdClient());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }

    @Override
    public void delete(ClientEntity object) {

    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

}
