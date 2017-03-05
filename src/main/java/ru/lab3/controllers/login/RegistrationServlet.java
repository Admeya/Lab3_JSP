package ru.lab3.controllers.login;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.common.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.lab3.controllers.login.LoginServlet;
import ru.lab3.services.IClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;


/**
 * Created by Ирина on 23.02.2017.
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    private IClientService clientService;

    @Autowired(required = true)
    public void setClientService(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String clientName = req.getParameter("clientName");
        String clientSurname = req.getParameter("clientSurname");
        String clientMiddleName = req.getParameter("clientMiddleName");
        String clientPassport = req.getParameter("passport");
        String clientPhone = req.getParameter("phone");
        LocalDate clientBirthdate = Utils.StringToLocalDate(req.getParameter("birthdate"));

        ClientEntity newClient = new ClientEntity(clientName, clientSurname, clientMiddleName, clientBirthdate,
                clientPassport, clientPhone, login, password);

        if (clientService.registration(newClient)) {
            logger.trace("true");
            resp.sendRedirect("/tour/login");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
