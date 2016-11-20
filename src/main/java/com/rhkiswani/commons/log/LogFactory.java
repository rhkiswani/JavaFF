package com.rhkiswani.commons.log;

import com.rhkiswani.commons.reflection.ReflectionUtil;

public class LogFactory {

    private LogFactory() {
        // Do nothing
    }

    public static SmartLogger getLog(Class<?> clazz) {
        if (ReflectionUtil.isPresent("org.slf4j.Logger")) {
            return new SmartLogger(new Slf4jLog(org.slf4j.LoggerFactory.getLogger(clazz)));
        } else {
            return new SmartLogger(new DefaultLog(java.util.logging.Logger.getLogger(clazz.getName())));
        }
    }
}
