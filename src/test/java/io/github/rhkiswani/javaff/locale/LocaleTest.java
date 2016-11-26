package io.github.rhkiswani.javaff.locale;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.locale.exceptions.LocaleException;
import org.junit.Test;

import java.util.Locale;

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
        assertThat(worker.getLocale()).isEqualTo(Locale.US);
        try{
            assertThat(worker.getString(null, null)).isNull();
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(LocaleException.class).hasMessage("Cant find bundle for base name messages, locale en_US");
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

    @Test
    public void testLocaleWorkerBuilder() throws Exception {
        LocaleWorker localeWorker = new LocaleWorkerBuilder().name("messages").path("/app").locale(Locale.CANADA).build();
        assertThat(localeWorker).isInstanceOf(DefaultLocaleWorker.class);
        DefaultLocaleWorker defaultLocaleWorker = (DefaultLocaleWorker) localeWorker;
        assertThat(defaultLocaleWorker.getName()).isEqualTo("messages");
        assertThat(defaultLocaleWorker.getPath()).isEqualTo("app/");
        assertThat(defaultLocaleWorker.getLocale()).isEqualTo(Locale.CANADA);
    }

    @Test
    public void testLocaleWorkerBuilderException() throws Exception {
        try {
            new LocaleWorkerBuilder().name("Kiswani").path("/SS").locale(Locale.CANADA).build();
        } catch (Exception e){
            assertThat(e).isInstanceOf(LocaleException.class).hasMessage("Cant find bundle for base name SS/Kiswani, locale en_CA");
        }
    }

    @Test
    public void testLocaleWorkerFactory() throws Exception {
        assertThat(LocaleWorkersFactory.instance() == LocaleWorkersFactory.instance()).isEqualTo(true);
    }
}
