package controllers;

import Entities.EmployeeEntity;
import common.EmployeeDAOException;
import common.Mailer;
import org.apache.log4j.Logger;
import services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class VIPLoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(VIPLoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("viplogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if (login != null && password != null) {
            try {
                EmployeeEntity employee = EmployeeService.isAuthorize(login, password);
                if (employee != null) {
                    req.setAttribute("Client", employee);

                    RequestDispatcher reqD = null;
                    if (employee.getRole().equals(EmployeeEntity.roleUser)) {
                        session.setAttribute("EMPLOYER", employee);
                        reqD = getServletContext().getRequestDispatcher("/privateCabinetEmployee.jsp");
                    } else if (employee.getRole().equals(EmployeeEntity.roleAdmin)) {
                        session.setAttribute("ADMIN", employee);
//                        String email = employee.getEmail();
//                        Mailer.sendMail(email, "admin logined", "wow!!!");
                        List<EmployeeEntity> employees = EmployeeService.selectAll();
                        req.setAttribute("Employees", employees);
                        reqD = getServletContext().getRequestDispatcher("/privateCabinetAdmin.jsp");
                    }
                    reqD.forward(req, resp);
                } else {
                    logger.trace("false");
                    ((HttpServletResponse) resp).sendRedirect("/tour/viperror.jsp");
                }
            } catch (EmployeeDAOException e) {
                e.printStackTrace();
            }
        }

    }
}
