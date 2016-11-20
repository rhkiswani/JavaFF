package com.rhkiswani.commons.format;

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
        return (T) FormatterManager.instance().getFormatter(obj).format(String.valueOf(obj), params);
    }
}
