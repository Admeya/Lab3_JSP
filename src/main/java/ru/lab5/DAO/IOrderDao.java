package ru.lab5.DAO;

import ru.lab5.Entities.Order;
import ru.lab5.POJO.OrderDTO;

import java.util.List;

public interface IOrderDao {
    boolean insert(OrderDTO order);

    List<Order> selectByPK(int idOrder);
}
