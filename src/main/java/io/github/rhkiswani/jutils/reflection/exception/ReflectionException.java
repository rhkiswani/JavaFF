package io.github.rhkiswani.jutils.reflection.exception;

import io.github.rhkiswani.jutils.exceptions.SmartException;

public class ReflectionException extends SmartException{

    public ReflectionException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public ReflectionException(Throwable e) {
        super(e);
    }
}
