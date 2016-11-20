package io.github.rhkiswani.jutils.log;

import org.slf4j.Logger;

class Slf4jLog implements Log {
    private final Logger logger;

    public Slf4jLog(Logger logger) {
        this.logger = logger;
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Exception e) {
        logger.error(message, e);
    }
}
