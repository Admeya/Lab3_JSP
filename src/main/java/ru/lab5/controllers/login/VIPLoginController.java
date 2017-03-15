package ru.lab5.controllers.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.exceptions.EmployeeDAOException;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IEmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class VIPLoginController {
    private static Logger logger = Logger.getLogger(VIPLoginController.class);

    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @RequestMapping(value = "/viplogin", method = RequestMethod.GET)
    public ModelAndView getRequestPage() {
        return new ModelAndView("viplogin");
    }

    @RequestMapping(value = "/viplogin", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView postRequestPage(HttpServletRequest req, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
        ModelAndView modelAndView = null;
        HttpSession session = req.getSession();
        if (login != null && password != null) {
            try {
                EmployeeEntity employee = employeeService.isAuthorize(login, password);
                if (employee != null) {
                    req.setAttribute("Client", employee);
                    if (employee.getRole().equals(EmployeeEntity.roleUser)) {
                        session.setAttribute("EMPLOYER", employee);
                        modelAndView = new ModelAndView("redirect:/lkEmpl");
                    } else if (employee.getRole().equals(EmployeeEntity.roleAdmin)) {
                        session.setAttribute("ADMIN", employee);
                        List<EmployeeEntity> employees = employeeService.selectAll();
                        req.setAttribute("Employees", employees);
                        modelAndView = new ModelAndView("redirect:/lkAdmin");
                    }
                } else {
                    modelAndView = new ModelAndView("viperror");
                    throw new ExceptionHandling("I don't get instance of Employee");
                }
            } catch (EmployeeDAOException e) {
                logger.error(e);
                modelAndView = new ModelAndView("error");
                throw new ExceptionHandling("I don't get login or password");
            }
        }
        return modelAndView;
    }
}
