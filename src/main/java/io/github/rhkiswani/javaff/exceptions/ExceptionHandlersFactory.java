package io.github.rhkiswani.javaff.exceptions;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

public class ExceptionHandlersFactory extends AbstractFactory<ExceptionHandler>{
    private static ExceptionHandlersFactory instance = new ExceptionHandlersFactory();

    private ExceptionHandlersFactory(){
    }

    public static ExceptionHandlersFactory instance(){
        return instance;
    }

    public void handle(Throwable t){
        getHandlerFor(Throwable.class).handle(t);
    }

    @Override
    public ExceptionHandler getDefault(Class targetClazz) {
        return new DefaultExceptionHandler();
    }
}
