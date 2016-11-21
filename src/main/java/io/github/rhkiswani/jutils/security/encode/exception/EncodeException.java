package io.github.rhkiswani.jutils.security.encode.exception;

import io.github.rhkiswani.jutils.exceptions.SmartException;

public class EncodeException extends SmartException{

    public EncodeException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public EncodeException(Throwable e) {
        super(e);
    }
}
