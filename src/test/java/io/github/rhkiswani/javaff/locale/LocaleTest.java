package io.github.rhkiswani.javaff.locale;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import org.junit.Test;

import java.util.Locale;
import java.util.MissingResourceException;

import static org.assertj.core.api.Assertions.assertThat;
public class LocaleTest {

    @Test
    public void testSetPath() throws Exception {
        DefaultLocaleWorker worker = new DefaultLocaleWorker();
        worker.setPath("kiswani/new");
        assertThat(worker.getPath()).isEqualTo("kiswani/new/");
        worker.setPath("/");
        assertThat(worker.getPath()).isEqualTo("");
        worker.setPath("/kiswani/kiswani");
        assertThat(worker.getPath()).isEqualTo("kiswani/kiswani/");
        worker.setPath(null);
        assertThat(worker.getPath()).isEqualTo("");
    }

    @Test
    public void testDefaultWorker() throws Exception {
        DefaultLocaleWorker worker = new DefaultLocaleWorker();
        assertThat(worker.getPath()).isEqualTo("");
        assertThat(worker.getName()).isEqualTo("messages");
        assertThat(worker.getExtensions()).isEqualTo(".properties");
        assertThat(worker.getLocale()).isEqualTo(Locale.US);
        try{
            assertThat(worker.getString(null, null)).isNull();
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(MissingResourceException.class).hasMessage("Can't find bundle for base name messages, locale en_US");
        }
        worker.setPath("app");
        assertThat(worker.getString(null, null)).isNull();
    }

    @Test
    public void testLocaleUtil() throws Exception {
        assertThat(LocaleUtil.getString(SmartException.EXCEEDS_LIMIT, "Array", 1000)).isEqualTo("Array MaxSize is 1,000");
        assertThat(LocaleUtil.getString(SmartException.HTTP_ERROR, "google.com")).isEqualTo("failed to connect to google.com");
        assertThat(LocaleUtil.getString("LOCALIZED_MSG", "Kiswani")).isEqualTo("this is localized msg from messages_en.properties thanks for Mr Kiswani");
        assertThat(LocaleUtil.getString(null)).isNull();
        assertThat(LocaleUtil.getString("LOCALIZED_MSG", null)).isEqualTo("this is localized msg from messages_en.properties thanks for Mr {0}");
    }
}
