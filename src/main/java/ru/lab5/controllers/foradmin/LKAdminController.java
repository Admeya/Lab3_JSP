package ru.lab5.controllers.foradmin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.services.IEmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class LKAdminController {
    private static Logger logger = Logger.getLogger(LKAdminController.class);

    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        EmployeeEntity user = (EmployeeEntity) session.getAttribute("ADMIN");
        req.setAttribute("Client", user);
    }

    @RequestMapping(value = "/lkAdmin", method = RequestMethod.GET)
    public ModelAndView getEditLKCLientPage(HttpServletRequest req, @RequestParam(name = "idEmpl", required = false) Integer idEmpl) {
        catchUser(req);
        ModelAndView modelAndView = null;
        if (idEmpl != null) {
            if (employeeService.deleteEmployee(idEmpl)) {
                modelAndView = new ModelAndView("redirect:/lkAdmin");
            } else {
                modelAndView = new ModelAndView("error");
            }
        } else {
            List<EmployeeEntity> employees = employeeService.selectAll();
            modelAndView = new ModelAndView("privateCabinetAdmin");
            modelAndView.addObject("Employees", employees);
        }
        return modelAndView;
    }
}
