package ru.lab3.controllers.for_employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.EmployeeEntity;
import ru.lab3.Entities.OrderEntity;
import ru.lab3.common.Utils;
import ru.lab3.services.IClientService;
import ru.lab3.services.IEmployeeService;
import ru.lab3.services.IOrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class EditOrderServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditOrderServlet.class);

    private IOrderService orderService;
    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEmpl = req.getParameter("idEmpl");
        if (idEmpl != null) {
            //Заказы в работе
            int idSotr = Integer.parseInt(idEmpl);
            List<OrderEntity> ordersThisEmployee = orderService.getOrdersByEmployee(idSotr);
            req.setAttribute("myOrders", ordersThisEmployee);

            //Новые заказы
            int idDefaultEmployee = employeeService.getIdByParam(EmployeeEntity.columnName, "Default");
            List<OrderEntity> newOrders = orderService.getOrdersByEmployee(idDefaultEmployee);
            req.setAttribute("newOrders", newOrders);

            req.getRequestDispatcher("/viewOrders.jsp").forward(req, resp);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
