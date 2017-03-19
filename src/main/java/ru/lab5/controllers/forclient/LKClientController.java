package ru.lab5.controllers.forclient;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Client;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class LKClientController extends HttpServlet {

    private static Logger logger = Logger.getLogger(LKClientController.class);

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Client user = (Client) session.getAttribute("PRINCIPAL");
        logger.trace("Hello from LKCLientServlet " + user);
        req.setAttribute("Client", user);
    }

    @RequestMapping(value = "/lkClient", method = RequestMethod.GET)
    public ModelAndView getLKCLientPage(HttpServletRequest req) {
        catchUser(req);
        return new ModelAndView("privateCabinetClient");
    }

    @RequestMapping(value = "/lkClient", method = RequestMethod.POST)
    public void postLKCLientPage(HttpServletRequest req) {
        catchUser(req);
    }
}
