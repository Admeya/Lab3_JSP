package ru.lab5.controllers.forclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.ClientEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IClientService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class ViewLKController {
    private static Logger logger = Logger.getLogger(ViewLKController.class);

    private IClientService clientService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/viewLKClient", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView getRequestPage(@RequestParam(name = "idClient", required = false) Integer idClient) {
        ModelAndView modelAndView = null;
        if (idClient != null) {
            modelAndView = new ModelAndView("viewLK");
            ClientEntity client = clientService.getClientByID(idClient);
            modelAndView.addObject("Client", client);
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I can't open LK Client");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/viewLKClient", method = RequestMethod.POST)
    public ModelAndView postRequestPage(@RequestParam(name = "clientId") Integer clientId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/editClient?idClient=" + clientId);
        return modelAndView;
    }
}
