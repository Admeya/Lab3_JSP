package model.DAO;

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

    public List<T> selectAll();

    public T selectByPK(int value, String columnName, T object);

    public boolean insert(T object);

    public boolean update(T object);

    public boolean deleteById(String columnName, int key);

    public void deleteAll();


}
