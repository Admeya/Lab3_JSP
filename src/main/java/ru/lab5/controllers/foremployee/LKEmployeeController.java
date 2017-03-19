package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class LKEmployeeController {
    private static Logger logger = Logger.getLogger(LKEmployeeController.class);

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Employee user = (Employee) session.getAttribute("EMPLOYER");
        logger.trace("this session for " + user);
        req.setAttribute("Employee", user);
    }

    @RequestMapping(value = "/lkEmpl", method = RequestMethod.GET)
    public ModelAndView getRequestPage(HttpServletRequest req) {
        catchUser(req);
        return new ModelAndView("privateCabinetEmployee");
    }
}
