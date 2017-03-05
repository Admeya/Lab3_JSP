package ru.lab3.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.TourEntity;
import ru.lab3.services.ITourService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ирина on 22.02.2017.
 */
public class MainServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(MainServlet.class);

    private ITourService tourService;

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    void catchTour(HttpServletRequest req) {
        List<TourEntity> tours = tourService.getAllTours();
        req.setAttribute("Tours", tours);
    }

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        ClientEntity user = (ClientEntity) session.getAttribute("PRINCIPAL");
        logger.trace("Hello from LKCLientServlet " + user);
        req.setAttribute("Client", user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchTour(req);
        catchUser(req);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchUser(req);
    }

}
