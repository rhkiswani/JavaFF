package com.rhkiswani.commons.format.exception;

import com.rhkiswani.commons.exceptions.SmartException;

public class FormatException extends SmartException{

    public FormatException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public FormatException(Throwable e) {
        super(e);
    }
}
