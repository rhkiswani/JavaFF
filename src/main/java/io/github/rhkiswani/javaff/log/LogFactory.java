package io.github.rhkiswani.javaff.log;

import io.github.rhkiswani.javaff.decetor.ApiDetectorUtil;
import io.github.rhkiswani.javaff.factory.AbstractFactory;

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
