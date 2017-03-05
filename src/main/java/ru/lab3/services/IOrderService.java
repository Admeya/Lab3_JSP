package ru.lab3.services;

import ru.lab3.Entities.OrderEntity;

import java.util.List;

/**
 * Created by Ирина on 02.03.2017.
 */
public interface IOrderService {
    boolean isInsert(OrderEntity order);

    List<OrderEntity> getOrdersByEmployee(int idEmployee);
}
