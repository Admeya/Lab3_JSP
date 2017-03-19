package ru.lab5.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.lab5.Entities.Client;
import ru.lab5.Entities.Tour;
import ru.lab5.services.ITourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
@Controller
public class MainController {
    private static Logger logger = Logger.getLogger(MainController.class);

    private ITourService tourService;

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    void catchTour(HttpServletRequest req) {
        List<Tour> tours = tourService.getAllTours();
        req.setAttribute("Tours", tours);
    }

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        Client user = (Client) session.getAttribute("PRINCIPAL");
        req.setAttribute("Client", user);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_CLIENT", "ROLE_ADMIN", "ROLE_ANONYMOUS"})
    public ModelAndView getRequestPage(HttpServletRequest req) {
        catchTour(req);
        catchUser(req);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    @Secured({"ROLE_USER", "ROLE_CLIENT", "ROLE_ADMIN", "ROLE_ANONYMOUS"})
    public ModelAndView postRequestPage(HttpServletRequest req) {
        catchUser(req);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_CLIENT", "ROLE_ADMIN", "ROLE_ANONYMOUS"})
    public ModelAndView index(HttpServletRequest req) {
        catchTour(req);
        catchUser(req);
        return new ModelAndView("index");
    }
}
