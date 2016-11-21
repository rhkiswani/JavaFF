package io.github.rhkiswani.javaff.log;

import java.util.logging.Level;
import java.util.logging.Logger;

class DefaultLog implements Log {
    private Logger logger = null ;

    public DefaultLog(Class clazz){
        this.logger = Logger.getLogger(clazz.getName());
    }

    public void debug(String message, Object... params) {
        logger.log(Level.FINE, message);
    }

    public void info(String message, Object... params) {
        logger.log(Level.INFO, message);
    }

    public void warn(String message, Object... params) {
        logger.log(Level.WARNING, message);
    }

    public void error(String message, Object... params) {
        logger.log(Level.SEVERE, message);
    }

    public void error(String message, Exception e, Object... params) {
        logger.log(Level.SEVERE, message, e);
    }

}
