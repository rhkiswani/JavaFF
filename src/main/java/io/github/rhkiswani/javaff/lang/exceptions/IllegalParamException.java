package io.github.rhkiswani.javaff.lang.exceptions;

import io.github.rhkiswani.javaff.exceptions.SmartException;

public class IllegalParamException extends SmartException {
    public IllegalParamException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

}
