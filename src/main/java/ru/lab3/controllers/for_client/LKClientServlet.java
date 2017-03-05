package ru.lab3.controllers.for_client;

import org.apache.log4j.Logger;
import ru.lab3.Entities.ClientEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ирина on 22.02.2017.
 */
public class LKClientServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LKClientServlet.class);

    void catchUser(HttpServletRequest req) {
        HttpSession session = ((HttpServletRequest) req).getSession();
        ClientEntity user = (ClientEntity) session.getAttribute("PRINCIPAL");
        logger.trace("Hello from LKCLientServlet " + user);
        req.setAttribute("Client", user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchUser(req);
        req.getRequestDispatcher("/privateCabinetClient.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        catchUser(req);
    }
}
