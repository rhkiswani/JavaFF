package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;

import java.util.Date;

public class FormatUtil {

    private FormatUtil(){

    }

    public static String formatString(String str, Object... params){
        return format(str, params);
    }

    public static String formatNumber(Number num){
        return format(num);
    }

    public String formatDate(Date date, Object... params){
        return format(date);
    }

    public static <T>T format(Object obj, Object... params){
        if (obj == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "Object");
        }
        return (T) FormatFactory.instance().getHandlerFor(obj.getClass()).format(String.valueOf(obj), params);
    }
}
