package ru.lab5.controllers.foremployee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.CountryEntity;
import ru.lab5.Entities.EmployeeEntity;
import ru.lab5.services.ICountryService;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class EditCountryController {
    private static Logger logger = Logger.getLogger(EditCountryController.class);

    private ICountryService countryService;

    @Autowired(required = true)
    public void setCountryService(ICountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/editCountry", method = RequestMethod.GET)
    public ModelAndView getEditCountryPage(@RequestParam(name = "idCountry", required = false) Integer idCountry) {
        ModelAndView modelAndView = null;
        if (idCountry != null) {
            modelAndView = new ModelAndView("editCountries");
            CountryEntity country = countryService.getCountryByID(idCountry);
            modelAndView.addObject("Country", country);
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editCountry", method = RequestMethod.POST)
    public ModelAndView postEditCountryPage(@ModelAttribute("Country") CountryEntity country) {
        ModelAndView modelAndView = null;
        if (countryService.updateCountry(country)) {
            modelAndView = new ModelAndView("redirect:/viewCountry");
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }

}
