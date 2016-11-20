package com.rhkiswani.commons.format;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class FormatterManager {

    private static FormatterManager factory = new FormatterManager();
    private ConcurrentMap<Class, Formatter> classFormatterMap = new ConcurrentHashMap<>();

    private FormatterManager(){
        add(Date.class, new DateFormatter());
        add(String.class, new StringFormatter());
        add(Number.class, new NumberFormatter());
    }

    public static FormatterManager instance(){
        return factory;
    }

    public void add(Class targetClass, Formatter formatter){
        classFormatterMap.put(targetClass, formatter);
    }

    public Formatter remove(Class targetClass){
        return classFormatterMap.remove(targetClass);
    }

    public Formatter getFormatter(Object obj){
        for (Class aClass : classFormatterMap.keySet()) {
            if (aClass.isInstance(obj)){
                return classFormatterMap.get(aClass);
            }
        }
        return new StringFormatter();
    }
}
