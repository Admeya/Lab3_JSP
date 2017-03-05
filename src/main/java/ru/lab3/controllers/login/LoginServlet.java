package ru.lab3.controllers.login;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.Entities.TourEntity;
import ru.lab3.common.ClientDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.services.IClientService;
import ru.lab3.services.ITourService;

import javax.servlet.RequestDispatcher;
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
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    private IClientService clientService;
    private ITourService tourService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired(required = true)
    public void setTourService(ITourService tourService) {
        this.tourService = tourService;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if (login != null && password != null) {
            try {
                ClientEntity client = clientService.isAuthorize(login, password);
                if (client != null) {
                    req.setAttribute("Client", client);
                    session.setAttribute("PRINCIPAL", client);
                    resp.sendRedirect("/tour/index");
                } else {
                    logger.trace("false");
                    ((HttpServletResponse) resp).sendRedirect("/tour/error.jsp");
                }
            } catch (ClientDAOException e) {
                logger.error(e);
                resp.sendRedirect("/error.jsp");
            }
        }

    }
}
