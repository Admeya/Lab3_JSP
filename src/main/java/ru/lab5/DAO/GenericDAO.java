package ru.lab5.DAO;

/**
 * Created by Ирина on 17.02.2017.
 */

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD - операции
 *
 * @param <T> тип передаваемого объекта
 */
public interface GenericDAO<T extends Serializable> {
    Connection getConnection();

    void setConnection(Connection connection);

    List<T> selectAll();

    T selectByPK(int value, String columnName, T object);

    boolean insert(T object);

    boolean update(T object);

    boolean deleteById(String columnName, int key);

    void deleteAll();

    List<T> selectByLoginAndPassword(String login, String pass);

    default int selectIdByParam(String columnName, String value) {
        return 0;
    }

    default List<T> selectById(int key, String columnName) {
        return new ArrayList<T>();
    }
}
