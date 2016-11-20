package io.github.rhkiswani.jutils.json.exceptions;

import io.github.rhkiswani.jutils.exceptions.SmartException;

public class JsonException extends SmartException{
    public JsonException(String errorMsg, Object... errorMsgParams) {
        super(errorMsg, errorMsgParams);
    }

    public JsonException(Throwable e) {
        super(e);
    }
}
