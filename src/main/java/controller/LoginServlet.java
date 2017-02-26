package controller;

import Entities.ClientEntity;
import common.ClientDAOException;
import org.apache.log4j.Logger;
import services.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ирина on 22.02.2017.
 */
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if (login != null && password != null) {
            try {
                ClientEntity client = ClientService.isAuthorize(login, password);
                if (client != null) {
                    req.setAttribute("Client", client);
                    session.setAttribute("PRINCIPAL", client);
                    RequestDispatcher reqD = getServletContext().getRequestDispatcher("/privateCabinetClient.jsp");
                    reqD.forward(req, resp);
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
