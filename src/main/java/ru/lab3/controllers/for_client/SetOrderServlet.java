package ru.lab3.controllers.for_client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.Entities.EmployeeEntity;
import ru.lab3.Entities.OrderEntity;
import ru.lab3.services.IEmployeeService;
import ru.lab3.services.IOrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Ирина on 22.02.2017.
 */
public class SetOrderServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(SetOrderServlet.class);

    private IEmployeeService employeeService;
    private IOrderService orderService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired(required = true)
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idClient = req.getParameter("idClient");

        if (idClient != null) {
            int idCl = Integer.parseInt(idClient);
            int idTour = Integer.parseInt(req.getParameter("idTour"));
            int idDefaultEmployee = employeeService.getIdByParam(EmployeeEntity.columnName, "Default");

            LocalDate localDate = LocalDate.now();

            OrderEntity order = new OrderEntity();
            order.setIdClient(idCl);
            order.setIdTour(idTour);
            order.setIdEmployee(idDefaultEmployee);
            order.setCheckoutDate(localDate);

            if (orderService.isInsert(order)) {
                req.getRequestDispatcher("/setOrder.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("error.jsp");
            }
        } else {
            resp.sendRedirect("/tour/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
