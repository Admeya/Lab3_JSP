package ru.lab5.controllers.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Client;
import ru.lab5.exceptions.ClientDAOException;
import ru.lab5.services.IClientService;
import ru.lab5.services.ITourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    private IClientService clientService;
    private ITourService tourService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getRequestPage() {
        return new ModelAndView("login");
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView postRequestPage(HttpServletRequest req, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password) {
//        ModelAndView modelAndView = null;
//        HttpSession session = req.getSession();
//        if (login != null && password != null) {
//            try {
//                Client client = clientService.authorize(login, password);
//                if (client != null) {
//                    req.setAttribute("Client", client);
//                    session.setAttribute("PRINCIPAL", client);
//                    modelAndView = new ModelAndView("redirect:/index");
//                } else {
//                    modelAndView = new ModelAndView("error");
//                }
//            } catch (ClientDAOException e) {
//                logger.error(e);
//                modelAndView = new ModelAndView("error");
//            }
//        }
//        return modelAndView;
//    }
}
