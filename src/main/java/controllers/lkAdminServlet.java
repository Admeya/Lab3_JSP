package controllers;

import Entities.EmployeeEntity;
import org.apache.log4j.Logger;
import services.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class lkAdminServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(lkAdminServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<EmployeeEntity> employees = EmployeeService.selectAll();
        req.setAttribute("Employees", employees);
        req.getRequestDispatcher("privateCabinetAdmin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
