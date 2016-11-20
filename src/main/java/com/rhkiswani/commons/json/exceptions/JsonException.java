package com.rhkiswani.commons.json.exceptions;

import com.rhkiswani.commons.exceptions.SmartException;

public class JsonException extends SmartException{
    public JsonException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public JsonException(Throwable e) {
        super(e);
    }
}
