package io.github.rhkiswani.jutils.format;

import io.github.rhkiswani.jutils.format.exception.FormatException;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormatter extends DefaultFormatter<Date, String>{

    @Override
    protected String formatVal(Date date, Object... params) {
        if (params.length > 0 && params[0] != null){
            try {
                SimpleDateFormat format = new SimpleDateFormat(params[0].toString());
                return format.format(date);
            }catch (Throwable t){
                throw new FormatException(t);
            }
        } else {
            return MessageFormat.format("{0}", date);
        }
    }
}
