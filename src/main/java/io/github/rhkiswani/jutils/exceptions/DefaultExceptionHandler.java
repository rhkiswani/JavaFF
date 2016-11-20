package io.github.rhkiswani.jutils.exceptions;

class DefaultExceptionHandler implements ExceptionHandler{

    public void handle(Throwable t){
        t.printStackTrace();
    }
}
