package model.DAO;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T> DAO таблицы из БД
 */
public abstract class AbstractDao<T extends Serializable> implements GenericDAO<T> {
    static Logger logger = Logger.getLogger(AbstractDao.class);

    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public abstract String getTableName();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public String getDeleteAllQuery() {
        return "DELETE FROM public." + getTableName();
    }

    public String getSelectAllQuery() {
        return "SELECT * FROM public." + getTableName();
    }

    public abstract File getXMLPath();

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object);

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object);

    @Override
    public List<T> selectAll() {
        List<T> list = new ArrayList<T>();
        ResultSet rs = null;
        String sql = getSelectAllQuery();

        try (Statement statement = connection.createStatement()) {
            rs = statement.executeQuery(sql);
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("Возникла ошибка при извлечении данных из БД: " + sql, e);
        }
        return list;
    }

    @Override
    public void deleteAll() {
        String sql = getDeleteAllQuery();
        try (Statement statement = connection.createStatement()) {
            int count = statement.executeUpdate(sql);
            logger.trace(sql + " Delete " + count + " records");
        } catch (Exception e) {
            logger.error("Возникла ошибка при удалении данных из БД: " + sql, e);
        }
    }

    @Override
    public boolean insert(T object) {
        String sql = getInsertQuery();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            count = statement.executeUpdate();
            logger.trace(statement + " Insert " + count + " records");
        } catch (Exception e) {
            logger.error("Возникла ошибка при вставке записей в БД: " + sql, e);
        }
        if (count == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean update(T object) {
        String sql = getUpdateQuery();
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            count = statement.executeUpdate();
            logger.trace(statement + " Updated " + count + " records");
        } catch (Exception e) {
            logger.error("Возникла ошибка при обновлении записей в БД: " + sql, e);
        }
        if (count == 1)
            return true;
        else
            return false;
    }

    @Override
    public T selectByPK(int key, String columnName, T object) {
        List<T> list = null;
        String sql = getSelectAllQuery() + " WHERE " + columnName + " = " + key;
        try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = statement.executeQuery(sql);
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("Возникла ошибка при извлечении данных по первичному ключу из БД: " + sql, e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.iterator().next();
    }

    public List<T> selectByLoginAndPassword(String login, String pass) {
        List<T> list = new ArrayList<T>();
        String sql = getSelectAllQuery() + " where login = ? and password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ps.setString(1, login);
            ps.setString(2, pass);
            logger.trace("SQL in Action: " + ps.toString());
            ResultSet resultSet = ps.executeQuery();
            list = parseResultSet(resultSet);

        } catch (SQLException e) {
            logger.trace(e);
        }
        return list;
    }
}

