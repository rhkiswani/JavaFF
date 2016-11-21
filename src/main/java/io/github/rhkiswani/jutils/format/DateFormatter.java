package io.github.rhkiswani.jutils.format;

import io.github.rhkiswani.jutils.format.exception.FormatException;
import io.github.rhkiswani.jutils.log.Log;
import io.github.rhkiswani.jutils.log.LogFactory;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormatter extends DefaultFormatter<Date, String> {

    private final Log LOGGER = LogFactory.instance().getHandlerFor(this.getClass());

    @Override
    protected String formatVal(Date date, Object... params) {
        if (params.length > 0 && params[0] != null){
            try {
                SimpleDateFormat format = new SimpleDateFormat(params[0].toString());
                return format.format(date);
            }catch (Throwable t){
                LOGGER.error("Failed to format {0} due to {1} ", date, t.getMessage());
                throw new FormatException(t);
            }
        } else {
            return MessageFormat.format("{0}", date);
        }
    }
}
