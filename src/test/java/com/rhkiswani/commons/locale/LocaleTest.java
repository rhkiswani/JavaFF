package com.rhkiswani.commons.locale;

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
}
