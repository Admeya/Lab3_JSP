package ru.lab3.listeners;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Ирина on 24.02.2017.
 */
public class ApplicationLoadListener implements ServletContextListener {
    private static Logger logger = Logger.getLogger(ApplicationLoadListener.class);

//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        logger.trace("Site started");
//    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        //final Properties props = (Properties)springContext.getBean("myProps");
        logger.trace("Site started");
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("Site stopped");
    }
}
