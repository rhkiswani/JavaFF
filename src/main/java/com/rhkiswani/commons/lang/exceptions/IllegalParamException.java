package com.rhkiswani.commons.lang.exceptions;

import com.rhkiswani.commons.exceptions.SmartException;

public class IllegalParamException extends SmartException{
    public IllegalParamException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

}
