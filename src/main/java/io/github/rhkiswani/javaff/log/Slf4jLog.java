package io.github.rhkiswani.javaff.log;

import org.slf4j.Logger;

class Slf4jLog implements Log {
    private final Logger logger;

    public Slf4jLog(Class clazz){
        logger = org.slf4j.LoggerFactory.getLogger(clazz);
    }

    public Slf4jLog(Logger logger, Object... params) {
        this.logger = logger;
    }

    public void debug(String message, Object... params) {
        logger.debug(message);
    }

    public void info(String message, Object... params) {
        logger.info(message);
    }

    public void warn(String message, Object... params) {
        logger.warn(message);
    }

    public void error(String message, Object... params) {
        logger.error(message);
    }

    public void error(String message, Exception e, Object... params) {
        logger.error(message, e);
    }
}
