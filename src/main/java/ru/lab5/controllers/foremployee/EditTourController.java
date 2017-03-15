package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.TourEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.ITourService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditTourController {
    private static Logger logger = Logger.getLogger(EditTourController.class);

    private ITourService tourService;

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    @RequestMapping(value = "/editTour", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView getEditCountryPage(@RequestParam(name = "idTour", required = false) Integer idTour) {
        ModelAndView modelAndView = null;
        if (idTour != null) {
            modelAndView = new ModelAndView("editTours");
            TourEntity tour = tourService.getTourById(idTour);
            modelAndView.addObject("tours", tour);
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I don't get idTour");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editTour", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView postEditCountryPage(@ModelAttribute("Tour") TourEntity tour) {
        ModelAndView modelAndView = null;
        if (tourService.updateTour(tour)) {
            modelAndView = new ModelAndView("redirect:/viewTour");
        } else {
            modelAndView = new ModelAndView("error");
            throw new ExceptionHandling("I can't update Tour");
        }
        return modelAndView;
    }

}
