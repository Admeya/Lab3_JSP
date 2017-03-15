package ru.lab5.exceptions;

import org.apache.log4j.Logger;


/**
 * Created by Ирина on 08.03.2017.
 */

public class ExceptionHandling extends RuntimeException {
    Logger logger = Logger.getLogger(ExceptionHandling.class);
    private String exceptionMsg;

    public ExceptionHandling(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
        logger.error(exceptionMsg);
    }

    public String getExceptionMsg() {
        return this.exceptionMsg;
    }


}
