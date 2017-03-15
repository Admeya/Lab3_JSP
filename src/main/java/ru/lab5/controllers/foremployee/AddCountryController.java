package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.CountryEntity;
import ru.lab5.exceptions.ExceptionHandling;
import ru.lab5.services.ICountryService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class AddCountryController {

    private static Logger logger = Logger.getLogger(AddCountryController.class);
    private ICountryService countryService;

    @Autowired(required = true)
    public void setEmployeeService(ICountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/addCountry", method = RequestMethod.GET)
    public ModelAndView getLKCLientPage() {
        return new ModelAndView("addDestination");
    }

    @RequestMapping(value = "/addCountry", method = RequestMethod.POST)
    @ExceptionHandler({ExceptionHandling.class})
    public ModelAndView showRegistrationPage(@ModelAttribute("country") CountryEntity country) {
        ModelAndView modelAndView = null;
        if (countryService.addCountry(country)) {
            modelAndView = new ModelAndView("redirect:/viewCountry");
        } else {
            modelAndView = new ModelAndView("viperror");
            throw new ExceptionHandling("I can't add new Country");
        }
        return modelAndView;
    }


}
