package io.github.rhkiswani.jutils.lang.exceptions;

import io.github.rhkiswani.jutils.exceptions.SmartException;

public class IllegalParamException extends SmartException {
    public IllegalParamException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

}
