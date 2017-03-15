package ru.lab5.controllers.forclient;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.ClientEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IClientService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditLKController {
    private static Logger logger = Logger.getLogger(EditLKController.class);

    private IClientService clientService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = "/editClient", method = RequestMethod.GET)
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idClient") Integer idClient) {
        ModelAndView modelAndView = new ModelAndView("editLK");
        ClientEntity client = clientService.getClientByID(idClient);
        modelAndView.addObject("Client", client);
        return modelAndView;
    }

    @RequestMapping(value = "/editClient", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView getEditLKCLientPage(@ModelAttribute("client") ClientEntity client) {
        ModelAndView modelAndView = null;
        if (clientService.updateClient(client)) {
            modelAndView = new ModelAndView("redirect:/viewLKClient?idClient=" + client.getIdClient());
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I can't update Client info");
        }
        return modelAndView;
    }
}
