package ru.lab5.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab5.DAO.IOrderDao;
import ru.lab5.DAO.OrderDao;
import ru.lab5.Entities.Order;
import ru.lab5.POJO.OrderDTO;
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

    private IOrderDao orderDAO;

    @Autowired
    public OrderService(OrderDao orderDAO) {
        this.orderDAO = orderDAO;
    }

    public boolean isInsert(OrderDTO order) {
        return orderDAO.insert(order);
    }

    public List<Order> getOrdersByEmployee(int idEmployee) {
        return orderDAO.selectByPK(idEmployee);
    }
}
