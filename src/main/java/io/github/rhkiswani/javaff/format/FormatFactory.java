package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

import java.util.Date;

public class FormatFactory extends AbstractFactory<Formatter> {

    private static FormatFactory factory = new FormatFactory();

    private FormatFactory(){
        add(Date.class, new DateFormatter());
        add(String.class, new StringFormatter());
        add(Number.class, new NumberFormatter());
    }

    public static FormatFactory instance(){
        return factory;
    }

    public Formatter getDefault(Class targetClazz){
        return new StringFormatter();
    }
}
