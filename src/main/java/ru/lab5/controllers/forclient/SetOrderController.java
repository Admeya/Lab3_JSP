package ru.lab5.controllers.forclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.Entities.OrderEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IEmployeeService;
import ru.lab5.services.IOrderService;

import java.time.LocalDate;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class SetOrderController {
    private static Logger logger = Logger.getLogger(SetOrderController.class);

    private IEmployeeService employeeService;
    private IOrderService orderService;

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired(required = true)
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/setOrder", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idClient", required = false) Integer idClient, @RequestParam(name = "idTour", required = false) Integer idTour) {
        ModelAndView modelAndView = null;
        if (idClient != null) {
            int idDefaultEmployee = employeeService.getIdByParam(EmployeeEntity.columnName, "Default");
            LocalDate localDate = LocalDate.now();
            OrderEntity order = new OrderEntity(idDefaultEmployee, idClient, idTour, localDate);
            if (orderService.isInsert(order)) {
                modelAndView = new ModelAndView("setOrder");
            } else {
                modelAndView = new ModelAndView("error");
                throw new ExceptionHandling("I can't add new Order");
            }
        } else
            modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}
