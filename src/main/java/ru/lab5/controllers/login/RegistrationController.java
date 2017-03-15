package ru.lab5.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.ClientEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.lab5.services.IClientService;

import javax.servlet.http.HttpServlet;


/**
 * Created by Ирина on 23.02.2017.
 */
@Controller
public class RegistrationController extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginController.class);

    private IClientService clientService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRequestPage() {
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView showRegistrationPage(@ModelAttribute("client") ClientEntity client) {
        ModelAndView modelAndView = null;
        if (clientService.registration(client)) {
            modelAndView = new ModelAndView("redirect:/login");
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }

}
