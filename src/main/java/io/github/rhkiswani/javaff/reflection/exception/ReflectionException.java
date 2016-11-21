package io.github.rhkiswani.javaff.reflection.exception;

import io.github.rhkiswani.javaff.exceptions.SmartException;

public class ReflectionException extends SmartException{

    public ReflectionException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public ReflectionException(Throwable e) {
        super(e);
    }
}
