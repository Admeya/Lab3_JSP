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
import ru.lab5.Entities.Country;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.ICountryService;

import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class ViewCountryController {
    private static Logger logger = Logger.getLogger(ViewCountryController.class);

    private ICountryService countryService;

    @Autowired(required = true)
    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/viewCountry", method = RequestMethod.GET)
    @ExceptionHandler({ExceptionHandling.class})
    @Secured({"ROLE_USER"})
    public ModelAndView getEditLKCLientPage(@RequestParam(name = "idCountry", required = false) Integer idCountry) {
        ModelAndView modelAndView = new ModelAndView("viewCountries");
        if (idCountry != null) {
            if (countryService.deleteCountry(idCountry)) {
                modelAndView = new ModelAndView("redirect:/viewCountry");
            } else {
                modelAndView = new ModelAndView("error");
                throw new ExceptionHandling("I can't delete Country");
            }
        } else {
            List<Country> countries = countryService.getAllCountries();
            modelAndView.addObject("countries", countries);
        }
        return modelAndView;
    }

}
