package com.rhkiswani.commons.reflection.exception;

import com.rhkiswani.commons.exceptions.SmartException;

public class ReflectionException extends SmartException{

    public ReflectionException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public ReflectionException(Throwable e) {
        super(e);
    }
}
