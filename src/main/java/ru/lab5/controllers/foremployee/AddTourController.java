package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.DestinationEntity;
import ru.lab5.Entities.TourEntity;
import ru.lab5.services.IDestinationService;
import ru.lab5.services.ITourService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class AddTourController {

    private static Logger logger = Logger.getLogger(AddTourController.class);
    private ITourService tourService;

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.GET)
    public ModelAndView getAddTourPage() {
        return new ModelAndView("addTour");
    }

    @RequestMapping(value = "/addTour", method = RequestMethod.POST)
    public ModelAndView showRegistrationPage(@ModelAttribute("tour") TourEntity tour) {
        ModelAndView modelAndView = null;
        if (tourService.addTour(tour)) {
            modelAndView = new ModelAndView("redirect:/viewTour");
        } else {
            modelAndView = new ModelAndView("viperror");
        }
        return modelAndView;
    }
}