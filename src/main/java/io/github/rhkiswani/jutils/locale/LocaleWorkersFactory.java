package io.github.rhkiswani.jutils.locale;

import io.github.rhkiswani.jutils.factory.AbstractFactory;
import io.github.rhkiswani.jutils.reflection.ReflectionUtil;

public class LocaleWorkersFactory extends AbstractFactory<LocaleWorker>{
    private static LocaleWorkersFactory instance = new LocaleWorkersFactory();

    private LocaleWorkersFactory(){
        add(Throwable.class, new LocaleWorkerBuilder().path("exceptions/").build());
        add(Object.class, new LocaleWorkerBuilder().path("app/").build());
    }

    public static LocaleWorkersFactory instance(){
        return instance;
    }

    public String getString(String key, Object... params) {
        LocaleWorker worker = null;
        try {
            Class callerClass = ReflectionUtil.getCallerClass(1);
            if (callerClass.equals(LocaleUtil.class)){
                callerClass = ReflectionUtil.getCallerClass(2);
            }
            worker = getHandlerFor(callerClass);
        }catch (Exception e ){
            worker = getDefault(Object.class);
        }
        String val = worker.getString(key, params);
        if (val != null && !val.equals(key)){
            return val;
        }
        return key;
    }

    @Override
    public LocaleWorker getDefault(Class targetClazz) {
        return new LocaleWorkerBuilder().path("app/").build();
    }
}
