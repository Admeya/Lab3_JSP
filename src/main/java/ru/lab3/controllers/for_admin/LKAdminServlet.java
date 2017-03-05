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
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class LKAdminServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LKAdminServlet.class);

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
        String paramId = req.getParameter("idEmpl");

        if (paramId != null) {
            int empId = Integer.parseInt(paramId);
            if (employeeService.deleteEmployee(empId)) {
                logger.trace("true");
                resp.sendRedirect("/tour/lkAdmin");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else {

            List<EmployeeEntity> employees = employeeService.selectAll();
            req.setAttribute("Employees", employees);
            req.getRequestDispatcher("privateCabinetAdmin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
