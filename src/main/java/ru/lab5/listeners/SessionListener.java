package ru.lab5.listeners;

import ru.lab5.Entities.Employee;
import ru.lab5.common.Mailer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Ирина on 28.02.2017.
 */
public class SessionListener implements HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        Employee empl = (Employee) event.getSession().getAttribute("ADMIN");
        logger.trace("Session created " + empl);
        String email = empl.getEmail();
        Mailer.sendMail(email, "admin logined", "wow!!!");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
