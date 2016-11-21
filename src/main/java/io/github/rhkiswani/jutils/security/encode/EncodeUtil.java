package io.github.rhkiswani.jutils.security.encode;

import io.github.rhkiswani.jutils.exceptions.SmartException;
import io.github.rhkiswani.jutils.lang.exceptions.IllegalParamException;

public class EncodeUtil {

    private EncodeUtil(){

    }

    public static <T>T encodeString(String obj, Object... params){
        if (obj == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "String");
        }
        return (T) EncodeFactory.instance().getHandlerFor(obj.getClass()).encode(String.valueOf(obj), params);
    }
}
