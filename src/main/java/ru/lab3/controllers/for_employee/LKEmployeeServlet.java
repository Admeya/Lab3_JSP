package ru.lab3.controllers.for_employee;

import org.apache.log4j.Logger;
import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.EmployeeEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ирина on 22.02.2017.
 */
public class LKEmployeeServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LKEmployeeServlet.class);

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        EmployeeEntity user = (EmployeeEntity) session.getAttribute("EMPLOYER");
        logger.trace("this session for " + user);
        req.setAttribute("Employee", user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchUser(req);
        req.getRequestDispatcher("/privateCabinetEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchUser(req);
    }
}
