package ru.lab5.DAO;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.lab5.Entities.ClientEntity;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.common.SaltPassword;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Repository
public class EmployeeDao extends AbstractDao<EmployeeEntity> {
    public static final String GET_ID_BY_STRING_PARAM = "SELECT * FROM " + EmployeeEntity.tableName + " WHERE %s = ?";

    @Override
    public String getTableName() {
        return EmployeeEntity.tableName;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO " + EmployeeEntity.tableName + " (" + EmployeeEntity.columnName + "," + EmployeeEntity.columnSurname + "," +
                EmployeeEntity.columnPhone + ", " + EmployeeEntity.columnLogin + ", " + EmployeeEntity.columnPass +
                ", " + EmployeeEntity.columnMail + ", " + EmployeeEntity.columnRole + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE " + EmployeeEntity.tableName + " SET " + EmployeeEntity.columnName + " = ? ," + EmployeeEntity.columnSurname + " = ?," +
                EmployeeEntity.columnPhone + " = ?," + EmployeeEntity.columnLogin + " = ?," +
                EmployeeEntity.columnMail + " = ?, " + EmployeeEntity.columnRole + " = ? WHERE " +
                EmployeeEntity.columnId + " = ?";
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, EmployeeEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPhone());
            statement.setString(4, object.getLogin());
            statement.setString(5, SaltPassword.encryptPass(object.getPassword()));
            statement.setString(6, object.getEmail());
            statement.setString(7, object.getRole());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + EmployeeEntity.tableName, e);
        }
    }

    @Override
    public List<EmployeeEntity> parseResultSet(ResultSet rs) {
        LinkedList<EmployeeEntity> result = new LinkedList<EmployeeEntity>();
        try {
            if (!rs.next()) {
                return null;
            } else {
                rs.beforeFirst();
                while (rs.next()) {
                    EmployeeEntity employee = new EmployeeEntity(rs.getInt(EmployeeEntity.columnId), rs.getString(EmployeeEntity.columnName).trim(),
                            rs.getString(EmployeeEntity.columnSurname).trim(), rs.getString(EmployeeEntity.columnPhone).trim(),
                            rs.getString(EmployeeEntity.columnLogin).trim(), rs.getString(EmployeeEntity.columnPass).trim(),
                            rs.getString(EmployeeEntity.columnMail).trim(), rs.getString(EmployeeEntity.columnRole).trim());
                    result.add(employee);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + EmployeeEntity.tableName + " в объект ClientDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, EmployeeEntity object) {
        try {
            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getPhone());
            statement.setString(4, object.getLogin());
            statement.setString(5, object.getEmail());
            statement.setString(6, object.getRole());
            statement.setInt(7, object.getIdEmployee());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }

    public int selectIdByParam(String columnName, String value) {
        String sql = String.format(GET_ID_BY_STRING_PARAM, columnName);
        EmployeeEntity empl = null;
        try (PreparedStatement ps = getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ps.setString(1, value);
            sql = ps.toString();
            ResultSet rs = ps.executeQuery();
            List<EmployeeEntity> list = parseResultSet(rs);
            empl = list.iterator().next();
        } catch (Exception e) {
            logger.error("Возникла ошибка при извлечении данных по первичному ключу из БД: " + sql, e);
        }
        return empl.getIdEmployee();
    }

}
