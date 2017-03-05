package ru.lab3.controllers.for_admin;

import ru.lab3.Entities.EmployeeEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.services.IEmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ирина on 22.02.2017.
 */
public class AddEmployeeServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddEmployeeServlet.class);
    private IEmployeeService employeeService;

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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String role = req.getParameter("role");
        String emplSurname = req.getParameter("emplSurname");
        String emplName = req.getParameter("emplName");

        if ((login != "") && (password != "") && (email != "") && (phone != "") &&
                (emplSurname != "") && (emplName != "")) {
            EmployeeEntity empl = new EmployeeEntity(emplName, emplSurname, phone, login, password, email, role);

            if (employeeService.addEmployee(empl)) {
                logger.trace("true");
                resp.sendRedirect("/tour/lkAdmin");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("viperror.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("nullvalerror.jsp").forward(req, resp);
        }
    }
}
