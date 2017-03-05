package ru.lab3.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lab3.DAO.ClientDao;
import ru.lab3.DAO.OrderDao;
import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.OrderEntity;
import ru.lab3.common.ClientDAOException;
import ru.lab3.common.ConnectionPool;
import ru.lab3.controllers.for_admin.AddEmployeeServlet;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Ирина on 24.02.2017.
 */
@Service
public class OrderService implements IOrderService {
    static Connection conn = ConnectionPool.getInstance().getConnection();
    private static Logger logger = Logger.getLogger(AddEmployeeServlet.class);

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
