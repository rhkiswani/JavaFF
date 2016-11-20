package io.github.rhkiswani.jutils.exceptions;

import java.util.HashSet;
import java.util.Set;

public class ExceptionHandlersManager {

    private static ExceptionHandlersManager registry = new ExceptionHandlersManager();
    private Set<ExceptionHandler> handlers = new HashSet<ExceptionHandler>();

    private ExceptionHandlersManager(){
        add(new DefaultExceptionHandler());
    }

    public static ExceptionHandlersManager instance(){
        return registry;
    }

    public void add(ExceptionHandler exceptionHandler){
        handlers.add(exceptionHandler);
    }

    public Boolean remove(ExceptionHandler exceptionHandler){
        return handlers.remove(exceptionHandler);
    }

    public Set<ExceptionHandler> listHandlers(){
        return handlers;
    }

    public void handle(Throwable t){
        Set<ExceptionHandler> exceptionHandlers = listHandlers();
        for (ExceptionHandler exceptionHandler : exceptionHandlers) {
            exceptionHandler.handle(t);
        }
    }
}
