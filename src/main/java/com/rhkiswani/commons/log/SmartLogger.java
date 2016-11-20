package com.rhkiswani.commons.log;
import com.rhkiswani.commons.format.FormatUtil;

public class SmartLogger {
    private Log logger = null;

    public SmartLogger(Log logger){
        this.logger = logger;
    }

    public void debug(String message, Object... params) {
        logger.debug(FormatUtil.formatString(message, params));
    }

    public void info(String message, Object... params) {
        logger.info(FormatUtil.formatString(message, params));
    }

    public void warn(String message, Object... params) {
        logger.warn(FormatUtil.formatString(message, params));
    }

    public void error(String message, Object... params) {
        logger.error(FormatUtil.formatString(message, params));
    }

    public void error(String message, Exception e, Object... params) {
        logger.error(FormatUtil.formatString(message, params), e);
    }
}
