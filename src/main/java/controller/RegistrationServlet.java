package controller;

import Entities.ClientEntity;
import common.Utils;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


/**
 * Created by Ирина on 23.02.2017.
 */
public class RegistrationServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

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
        Date clientBirthdate = null;
        try {
            clientBirthdate = Utils.stringToUtilDate(req.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ClientEntity newClient = new ClientEntity(clientName, clientSurname, clientMiddleName, clientBirthdate,
                clientPassport, clientPhone, login, password);

        if (ClientService.registration(newClient)) {
            logger.trace("true");
            resp.sendRedirect("/tour/login");
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
