package ru.lab3.DAO;

import org.springframework.stereotype.Component;
import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.EmployeeEntity;
import ru.lab3.Entities.OrderEntity;
import ru.lab3.Entities.TourEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Component
public class OrderDao extends AbstractDao<OrderEntity> {

    public static final String GET_ALL_ORDERS = "SELECT o." + OrderEntity.columnId + ", o." + OrderEntity.columnIdEmployee + ", o." +
            OrderEntity.columnIdClient + ", o." + OrderEntity.columnIdTour + ", o." + OrderEntity.columnCheckoutDate +
            ", c." + ClientEntity.columnName + ", c." + ClientEntity.columnSurname + ", c." + ClientEntity.columnPhone + ", t." +
            TourEntity.columnName + " as tourName FROM public." + OrderEntity.tableName + " o left join " + ClientEntity.tableName + " c " +
            "on o." + OrderEntity.columnIdClient + " = c." + ClientEntity.columnIdClient + " left join " + TourEntity.tableName +
            " t on o." + OrderEntity.columnIdTour + " = t." + TourEntity.columnId;


    @Override
    public String getTableName() {
        return OrderEntity.tableName;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO public." + getTableName() + " (" + OrderEntity.columnIdEmployee + "," + OrderEntity.columnIdClient + "," +
                OrderEntity.columnIdTour + "," + OrderEntity.columnCheckoutDate + ") " +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public List<OrderEntity> parseResultSet(ResultSet rs) {
        LinkedList<OrderEntity> result = new LinkedList<OrderEntity>();
        try {
            while (rs.next()) {
                OrderEntity order = new OrderEntity();
                order.setIdOrder(rs.getInt(OrderEntity.columnId));
                order.setIdEmployee(rs.getInt(OrderEntity.columnIdEmployee));
                order.setIdClient(rs.getInt(OrderEntity.columnIdClient));
                order.setIdTour(rs.getInt(OrderEntity.columnIdTour));
                order.setCheckoutDate(rs.getDate(OrderEntity.columnCheckoutDate).toLocalDate());
                order.setNameClient(rs.getString(ClientEntity.columnName));
                order.setSurnameClient(rs.getString(ClientEntity.columnSurname));
                order.setPhoneClient(rs.getString(ClientEntity.columnPhone));
                order.setNameTour(rs.getString("tourName"));
                result.add(order);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception при парсинге записей из таблицы " + OrderEntity.tableName + " в объект OrderDAO:", e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, OrderEntity object) {
        try {
            statement.setInt(1, object.getIdEmployee());
            statement.setInt(2, object.getIdClient());
            statement.setInt(3, object.getIdTour());
            statement.setDate(4, java.sql.Date.valueOf(object.getCheckoutDate()));
        } catch (Exception e) {
            logger.error("Возникла ошибка при подготовке данных для вставки в таблицу " + OrderEntity.tableName, e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, OrderEntity object) {

    }

    public List<OrderEntity> selectById(int key, String columnName) {
        List<OrderEntity> list = null;
        String sql = GET_ALL_ORDERS + " WHERE o." + columnName + " = " + key;
        try (Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = statement.executeQuery(sql);
            list = parseResultSet(rs);
        } catch (Exception e) {
            logger.error("Возникла ошибка при извлечении данных по ключу из БД: " + sql, e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        return list;
    }
}
