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
        String paramId = req.getParameter("idEmpl");

        if (paramId != null) {
            int empId = Integer.parseInt(paramId);
            if (EmployeeService.deleteEmployee(empId)) {
                logger.trace("true");
                resp.sendRedirect("/tour/lkAdmin");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else {

            List<EmployeeEntity> employees = EmployeeService.selectAll();
            req.setAttribute("Employees", employees);
            req.getRequestDispatcher("privateCabinetAdmin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
