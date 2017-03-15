package ru.lab5.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.lab5.Entities.ClientEntity;
import org.springframework.stereotype.Component;
import ru.lab5.common.SaltPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                ClientEntity.columnPhone + " = ?, " + ClientEntity.columnLogin + " = ? WHERE " +
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
                    client.setBirthdate(rs.getDate(ClientEntity.columnBirthdate));
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
            statement.setDate(4, object.getBirthdate());
            statement.setString(5, object.getPassportSerNum());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getLogin());
            statement.setString(8, SaltPassword.encryptPass(object.getPass().trim()));
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
            statement.setDate(4, object.getBirthdate());
            statement.setString(5, object.getPassportSerNum());
            statement.setString(6, object.getPhone());
            statement.setString(7, object.getLogin());
            statement.setInt(8, object.getIdClient());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }
}
