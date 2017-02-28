package model.DAO;

import Entities.ClientEntity;
import Entities.EmployeeEntity;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class EmployeeDao extends AbstractDao<EmployeeEntity> {

    public EmployeeDao(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return EmployeeEntity.tableName;
    }

    @Override
    public File getXMLPath() {
        return new File("./Employee.xml");
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
                EmployeeEntity.columnPhone + " = ?," + EmployeeEntity.columnLogin + " = ?," + EmployeeEntity.columnPass + " = ?," +
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
            statement.setString(5, object.getPassword());
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
                    EmployeeEntity employee = new EmployeeEntity();
                    employee.setIdEmployee(rs.getInt(EmployeeEntity.columnId));
                    employee.setName(rs.getString(EmployeeEntity.columnName).trim());
                    employee.setSurname(rs.getString(EmployeeEntity.columnSurname).trim());
                    employee.setPhone(rs.getString(EmployeeEntity.columnPhone).trim());
                    employee.setLogin(rs.getString(EmployeeEntity.columnLogin).trim());
                    employee.setPassword(rs.getString(EmployeeEntity.columnPass).trim());
                    employee.setEmail(rs.getString(EmployeeEntity.columnMail).trim());
                    employee.setRole(rs.getString(EmployeeEntity.columnRole).trim());
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
            statement.setString(5, object.getPassword());
            statement.setString(6, object.getEmail());
            statement.setString(7, object.getRole());
            statement.setInt(8, object.getIdEmployee());
            logger.trace(statement);
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + ClientEntity.tableName, e);
        }
    }

}
