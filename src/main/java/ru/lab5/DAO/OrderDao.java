package ru.lab5.DAO;

import org.springframework.stereotype.Repository;
import ru.lab5.Entities.Client;
import ru.lab5.Entities.Order;
import ru.lab5.Entities.Tour;
import ru.lab5.POJO.OrderDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderDao implements IOrderDao {


    @Override
    public boolean insert(OrderDTO order) {
        return false;
    }

    @Override
    public List<Order> selectByPK(int idOrder) {
        return null;
    }
}
