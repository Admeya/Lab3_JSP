package controllers;

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
 * Created by Ирина on 22.02.2017.
 */
public class EditLKServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EditLKServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idClient = req.getParameter("idClient");
        if (idClient != null) {
            int idCl = Integer.parseInt(idClient);

            ClientEntity client = ClientService.getClientByID(idCl);
            req.setAttribute("Client", client);
            req.getRequestDispatcher("/editLK.jsp").forward(req, resp);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int clientId = Integer.parseInt(req.getParameter("idClient"));
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        String clientName = req.getParameter("clientName");
        String clientSurname = req.getParameter("clientSurname");
        String clientMiddleName = req.getParameter("clientMiddleName");
        String passport = req.getParameter("passport");
        String phone = req.getParameter("phone");
        Date birthdate = null;
        try {
            birthdate = Utils.stringToUtilDate(req.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ClientEntity cli = new ClientEntity(clientName, clientSurname, clientMiddleName, birthdate, passport, phone, login, pass);
        cli.setIdClient(clientId);

        if (ClientService.updateClient(cli)) {
            logger.trace("true");
            resp.sendRedirect("/tour/viewLK?idClient=" + clientId);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
