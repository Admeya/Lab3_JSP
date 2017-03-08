package ru.lab5.controllers.foradmin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.services.IEmployeeService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditEmployeeController {
    private static Logger logger = Logger.getLogger(EditEmployeeController.class);

    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/editEmpLK", method = RequestMethod.GET)
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idEmpl", required = false) Integer idEmpl) {
        ModelAndView modelAndView = null;
        if (idEmpl != null) {
            modelAndView = new ModelAndView("editEmployee");
            EmployeeEntity employee = employeeService.getClientByID(idEmpl);
            modelAndView.addObject("Employee", employee);
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editEmpLK", method = RequestMethod.POST)
    public ModelAndView getEditLKCLientPage(@ModelAttribute("employee") EmployeeEntity employee) {
        ModelAndView modelAndView = null;
        if (employeeService.updateEmpl(employee)) {
            modelAndView = new ModelAndView("redirect:/lkAdmin");
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }
}
