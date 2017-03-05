package ru.lab3.controllers.for_client;

import ru.lab3.Entities.ClientEntity;
import ru.lab3.common.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
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
 * Created by Ирина on 22.02.2017.
 */
public class EditLKServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditLKServlet.class);

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
        String idClient = req.getParameter("idClient");
        if (idClient != null) {
            int idCl = Integer.parseInt(idClient);

            ClientEntity client = clientService.getClientByID(idCl);
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
        LocalDate birthdate = Utils.StringToLocalDate(req.getParameter("birthdate"));

        ClientEntity cli = new ClientEntity(clientName, clientSurname, clientMiddleName, birthdate, passport, phone, login, pass);
        cli.setIdClient(clientId);

        if (clientService.updateClient(cli)) {
            logger.trace("true");
            resp.sendRedirect("/tour/viewLK?idClient=" + clientId);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
