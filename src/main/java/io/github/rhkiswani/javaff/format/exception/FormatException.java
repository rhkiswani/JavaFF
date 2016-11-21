package io.github.rhkiswani.javaff.format.exception;

import io.github.rhkiswani.javaff.exceptions.SmartException;

public class FormatException extends SmartException{

    public FormatException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public FormatException(Throwable e) {
        super(e);
    }
}
