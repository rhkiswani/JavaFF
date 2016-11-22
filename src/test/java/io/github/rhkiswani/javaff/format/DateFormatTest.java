package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.format.exception.FormatException;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.StrictAssertions.assertThat;

public class DateFormatTest extends AbstractFormatTest<Date>{

    private static final String DATE_FORMAT = "MM/dd/yy HH:mm a";
    private static final String DATE = "11/24/16 12:00 PM";

    @Test
    public void testDateFormatter() {
        testNulls(new DateFormatter(), getDate());
        assertThat(new DateFormatter().format(getDate(), DATE_FORMAT)).isEqualTo(DATE);
        try {
            new DateFormatter().format(getDate(), "dfghdfg");
        } catch (Exception e){
            assertThat(e).isInstanceOf(FormatException.class).hasMessage("Date format is not fit to 11/24/16 12:00 PM");
        }
        try {
            new DateFormatter().format(getDate(), "");
        } catch (Exception e){
            assertThat(e).isInstanceOf(FormatException.class).hasMessage("Date format is not fit to 11/24/16 12:00 PM");
        }
    }

    private Date getDate(){
        return getDate(DATE_FORMAT);
    }

    private Date getDate(String format){
        try {
            return new SimpleDateFormat(format).parse(DATE);
        } catch (ParseException e) {
            // never happen
            return null;
        }
    }
}
