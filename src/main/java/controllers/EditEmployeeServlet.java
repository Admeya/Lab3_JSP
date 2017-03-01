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
public class EditEmployeeServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditEmployeeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEmpl = req.getParameter("idEmpl");
        if (idEmpl != null) {
            int idCl = Integer.parseInt(idEmpl);

            EmployeeEntity employee = EmployeeService.getClientByID(idCl);
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

            if (EmployeeService.updateEmpl(empl)) {
                logger.trace("true");
                resp.sendRedirect("/tour/lkAdmin");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }
}
