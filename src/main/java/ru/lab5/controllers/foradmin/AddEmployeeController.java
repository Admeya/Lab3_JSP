package ru.lab5.controllers.foradmin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.services.IEmployeeService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class AddEmployeeController {

    private static Logger logger = Logger.getLogger(AddEmployeeController.class);
    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/addEmpLK", method = RequestMethod.POST)
    public ModelAndView showRegistrationPage(@ModelAttribute("employee") EmployeeEntity employee) {
        ModelAndView modelAndView = null;
        if (employeeService.addEmployee(employee)) {
            modelAndView = new ModelAndView("redirect:/lkAdmin");
        } else {
            modelAndView = new ModelAndView("viperror");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/addEmpLK", method = RequestMethod.GET)
    public ModelAndView getLKCLientPage() {
        return new ModelAndView("addDestination");
    }
}
