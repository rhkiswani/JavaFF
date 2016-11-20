package io.github.rhkiswani.jutils.format.exception;

import io.github.rhkiswani.jutils.exceptions.SmartException;

public class FormatException extends SmartException{

    public FormatException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public FormatException(Throwable e) {
        super(e);
    }
}
