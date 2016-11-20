package com.rhkiswani.commons.exceptions;

class DefaultExceptionHandler implements ExceptionHandler{

    public void handle(Throwable t){
        t.printStackTrace();
    }
}
