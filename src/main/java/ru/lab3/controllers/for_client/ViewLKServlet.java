package ru.lab3.controllers.for_client;

import ru.lab3.Entities.ClientEntity;
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

/**
 * Created by Ирина on 22.02.2017.
 */
public class ViewLKServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ViewLKServlet.class);

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
            req.getRequestDispatcher("/viewLK.jsp").forward(req, resp);
        } else {
            logger.trace("false");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int clientId = Integer.parseInt(req.getParameter("idClient"));
        resp.sendRedirect("/tour/editLK?idClient=" + clientId);

    }
}
