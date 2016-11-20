package io.github.rhkiswani.jutils.log;

import java.util.logging.Level;
import java.util.logging.Logger;

class DefaultLog implements Log {
    private final Logger logger;

    public DefaultLog(Logger logger) {
        this.logger = logger;
    }

    public void debug(String message) {
        logger.log(Level.FINE, message);
    }

    public void info(String message) {
        logger.log(Level.INFO, message);
    }

    public void warn(String message) {
        logger.log(Level.WARNING, message);
    }

    public void error(String message) {
        logger.log(Level.SEVERE, message);
    }

    public void error(String message, Exception e) {
        logger.log(Level.SEVERE, message, e);
    }


}
