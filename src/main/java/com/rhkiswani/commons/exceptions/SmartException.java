package com.rhkiswani.commons.exceptions;

import com.rhkiswani.commons.lang.Arrays;
import com.rhkiswani.commons.locale.LocaleUtil;

public class SmartException extends RuntimeException{


    public static final String NULL_VAL = "NULL_VAL";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String TYPE_ERROR = "TYPE_ERROR";
    public static final String EXCEEDS_LIMIT = "EXCEEDS_LIMIT";
    public static final String FORMAT_EXCEPTION = "FORMAT_EXCEPTION";
    private Object[] errorMsgParams = null;

    public SmartException(String message) {
        super(message);
    }

    public SmartException(String message, Object... errorMsgParams) {
        super(message);
        this.errorMsgParams = errorMsgParams;
    }

    public SmartException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (!Arrays.isEmpty(errorMsgParams)){
            return LocaleUtil.getString(super.getMessage(), errorMsgParams);
        } else {
            return LocaleUtil.getString(super.getMessage());
        }
    }
}
