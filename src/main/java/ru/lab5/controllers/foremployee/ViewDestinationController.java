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
import ru.lab5.Entities.Destination;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.IDestinationService;

import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class ViewDestinationController {
    private static Logger logger = Logger.getLogger(ViewDestinationController.class);

    private IDestinationService destinationService;

    @Autowired
    public void setDestinationService(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @RequestMapping(value = "/viewDest", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView getDestPage(@RequestParam(name = "idDest", required = false) Integer idDest) {
        ModelAndView modelAndView = new ModelAndView("viewDestinations");
        if (idDest != null) {
            if (destinationService.deleteDestination(idDest)) {
                modelAndView = new ModelAndView("redirect:/viewDest");
            } else {
                modelAndView = new ModelAndView("error");
                throw new ExceptionHandling("I can't delete Destination");
            }
        } else {
            List<Destination> dests = destinationService.getAllDests();
            modelAndView.addObject("destinations", dests);
        }
        return modelAndView;
    }

}
