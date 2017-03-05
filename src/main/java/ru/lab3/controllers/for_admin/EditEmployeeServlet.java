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
public class EditEmployeeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditEmployeeServlet.class);

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
        String idEmpl = req.getParameter("idEmpl");
        if (idEmpl != null) {
            int idCl = Integer.parseInt(idEmpl);

            EmployeeEntity employee = employeeService.getClientByID(idCl);
            req.setAttribute("Employee", employee);
            req.getRequestDispatcher("/editEmployee.jsp").forward(req, resp);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int clientId = Integer.parseInt(req.getParameter("idEmployee"));
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String emplName = req.getParameter("emplName");
        String emplSurname = req.getParameter("emplSurname");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String phone = req.getParameter("phone");

        if ((login != "") && (pass != "") && (email != "") && (phone != "") && (role != "") &&
                (emplSurname != "") && (emplName != "")) {

            EmployeeEntity empl = new EmployeeEntity(emplName, emplSurname, phone, login, pass, email, role);
            empl.setIdEmployee(clientId);

            if (employeeService.updateEmpl(empl)) {
                logger.trace("true");
                resp.sendRedirect("/tour/lkAdmin");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }
}
