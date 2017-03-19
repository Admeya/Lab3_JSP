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
import ru.lab5.Entities.Tour;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.ITourService;

import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class ViewTourController {
    private static Logger logger = Logger.getLogger(ViewTourController.class);

    private ITourService tourService;

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    @RequestMapping(value = "/viewTour", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idTour", required = false) Integer idTour) {
        ModelAndView modelAndView = new ModelAndView("viewTours");
        if (idTour != null) {
            if (tourService.deleteTour(idTour)) {
                modelAndView = new ModelAndView("redirect:/viewTour");
            } else {
                modelAndView = new ModelAndView("error");
                throw new ExceptionHandling("I can't delete Tour");
            }
        } else {
            List<Tour> tours = tourService.getAllTours();
            modelAndView.addObject("tours", tours);
        }
        return modelAndView;
    }

}
