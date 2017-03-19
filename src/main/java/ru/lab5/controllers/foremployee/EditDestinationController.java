package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Destination;
import ru.lab5.POJO.DestinationDTO;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IDestinationService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditDestinationController {
    private static Logger logger = Logger.getLogger(EditDestinationController.class);

    private IDestinationService destinationService;

    @Autowired(required = true)
    public void setDestinationService(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @RequestMapping(value = "/editDest", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView getEditCountryPage(@RequestParam(name = "idDest", required = false) Integer idDest) {
        ModelAndView modelAndView = null;
        if (idDest != null) {
            modelAndView = new ModelAndView("editDestinations");
            Destination dest = destinationService.getDestByID(idDest);
            modelAndView.addObject("destinations", dest);
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I don't get idDest");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editDest", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView postEditCountryPage(@ModelAttribute("Destination") DestinationDTO dest) {
        ModelAndView modelAndView = null;
        if (destinationService.updateDestination(dest)) {
            modelAndView = new ModelAndView("redirect:/viewDest");
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I can't update Destination");
        }
        return modelAndView;
    }

}
