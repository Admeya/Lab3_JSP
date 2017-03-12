package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.GenericDAO;
import ru.lab5.DAO.OrderDao;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.Entities.OrderEntity;
import ru.lab5.common.ConnectionPool;
import ru.lab5.controllers.foradmin.AddEmployeeController;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class OrderService implements IOrderService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeController.class);

    private OrderDao orderDAO;

    @Autowired
    public OrderService(OrderDao orderDAO) {
        this.orderDAO = orderDAO;
    }

    public boolean isInsert(OrderEntity order) {
        orderDAO.setConnection(conn);
        return orderDAO.insert(order);
    }

    public List<OrderEntity> getOrdersByEmployee(int idEmployee) {
        orderDAO.setConnection(conn);
        return orderDAO.selectById(idEmployee, OrderEntity.columnIdEmployee);
    }
}
