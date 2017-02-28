package controllers;

import Entities.EmployeeEntity;
import org.apache.log4j.Logger;
import services.EmployeeService;

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


        EmployeeEntity empl = new EmployeeEntity(emplName, emplSurname, phone, login, password, email, role);

        if (EmployeeService.addEmployee(empl)) {
            logger.trace("true");
            resp.sendRedirect("/tour/lkAdmin");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("viperror.jsp").forward(req, resp);
        }
    }
}
