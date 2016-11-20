package io.github.rhkiswani.jutils.log;

import io.github.rhkiswani.jutils.reflection.ReflectionUtil;

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
