package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Employee;
import ru.lab5.Entities.Order;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IEmployeeService;
import ru.lab5.services.IOrderService;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditOrderController extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditOrderController.class);

    private IOrderService orderService;
    private IEmployeeService employeeService;

    @Autowired(required = true)
    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired(required = true)
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/editOrder", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idEmpl", required = false) Integer idEmpl) {
        ModelAndView modelAndView = null;
        if (idEmpl != null) {
            modelAndView = new ModelAndView("viewOrders");

            //Заказы в работе
            List<Order> ordersThisEmployee = orderService.getOrdersByEmployee(idEmpl);
            modelAndView.addObject("myOrders", ordersThisEmployee);

            //Новые заказы
            int idDefaultEmployee = employeeService.getIdByName("Default");
            List<Order> newOrders = orderService.getOrdersByEmployee(idDefaultEmployee);
            modelAndView.addObject("newOrders", newOrders);
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I don't get idEmpl");
        }
        return modelAndView;
    }

}
