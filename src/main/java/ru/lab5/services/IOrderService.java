package ru.lab5.services;

import ru.lab5.Entities.Order;
import ru.lab5.POJO.OrderDTO;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IOrderService {
    boolean isInsert(OrderDTO order);

    List<Order> getOrdersByEmployee(int idEmployee);
}
