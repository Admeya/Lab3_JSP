package ru.lab3.DAO;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.common.Utils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

@Component
public class ClientDao extends AbstractDao<ClientEntity> {

    @Override
    public String getTableName() {
        return ClientEntity.tableName;
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
        return "UPDATE " + ClientEntity.tableName + " SET " + ClientEntity.columnName + " = ? ," + ClientEntity.columnSurname + " = ?," +
                ClientEntity.columnMiddlename + " = ?," + ClientEntity.columnBirthdate + " = ?," + ClientEntity.columnPasport + " = ?," +
                ClientEntity.columnPhone + " = ?, " + ClientEntity.columnLogin + " = ?, " + ClientEntity.columnPass + " = ? WHERE " +
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
                    client.setBirthdate(rs.getDate(ClientEntity.columnBirthdate).toLocalDate());
                    client.setPassportSerNum(rs.getString(ClientEntity.columnPasport).trim());
                    client.setPhone(rs.getString(ClientEntity.columnPhone).trim());
                    client.setLogin(rs.getString(ClientEntity.columnLogin).trim());
                    client.setPass(rs.getString(ClientEntity.columnPass).trim());
                    result.add(client);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + ClientEntity.tableName + " в объект ClientDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, ClientEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getMiddlename());
            statement.setDate(4, java.sql.Date.valueOf(object.getBirthdate()));
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
            statement.setDate(4, java.sql.Date.valueOf(object.getBirthdate()));
            statement.setString(5, object.getPassportSerNum());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getLogin());
            statement.setString(8, object.getPass());
            statement.setInt(9, object.getIdClient());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }
}
