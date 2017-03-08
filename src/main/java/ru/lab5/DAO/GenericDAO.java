package ru.lab5.DAO;

/**
 * Created by Ирина on 17.02.2017.
 */

import java.io.Serializable;
import java.util.List;

/**
 * CRUD - операции
 *
 * @param <T> тип передаваемого объекта
 */
public interface GenericDAO<T extends Serializable> {

    List<T> selectAll();

    T selectByPK(int value, String columnName, T object);

    boolean insert(T object);

    boolean update(T object);

    boolean deleteById(String columnName, int key);

    void deleteAll();


}
