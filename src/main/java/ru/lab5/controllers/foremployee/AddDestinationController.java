package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.DestinationEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IDestinationService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class AddDestinationController {

    private static Logger logger = Logger.getLogger(AddDestinationController.class);
    private IDestinationService destinationService;

    @Autowired(required = true)
    public void setDestinationService(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @RequestMapping(value = "/addDest", method = RequestMethod.GET)
    public ModelAndView getLKCLientPage() {
        return new ModelAndView("addDestination");
    }

    @RequestMapping(value = "/addDest", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView showRegistrationPage(@ModelAttribute("country") DestinationEntity destination) {
        ModelAndView modelAndView = null;
        if (destinationService.addDestination(destination)) {
            modelAndView = new ModelAndView("redirect:/viewDest");
        } else {
            modelAndView = new ModelAndView("viperror");
            throw new ExceptionHandling("I can't add new Destination");
        }
        return modelAndView;
    }
}
