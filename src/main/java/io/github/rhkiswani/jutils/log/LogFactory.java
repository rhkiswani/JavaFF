package io.github.rhkiswani.jutils.log;

import io.github.rhkiswani.jutils.decetor.ApiDetectorUtil;
import io.github.rhkiswani.jutils.factory.AbstractFactory;

public class LogFactory extends AbstractFactory<Log>{
    private static LogFactory instance = new LogFactory();

    private LogFactory(){
    }

    public static LogFactory instance(){
        return instance;
    }

    @Override
    public Log getDefault(Class targetClazz) {
        if (ApiDetectorUtil.isSlf4jAvailable()){
            return new Slf4jLog(targetClazz);
        }
        return new DefaultLog(targetClazz.getClass());
    }
}
