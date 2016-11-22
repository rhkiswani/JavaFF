package io.github.rhkiswani.javaff.exceptions;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionsTest {

    String exceptionMsg = "dummyMsg";
    @Test
    public void testSmartException() throws Exception {
        try {
           throw new SmartException(SmartException.EXCEEDS_LIMIT, "Test Name", 1000000);
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(SmartException.class).hasMessage(FormatUtil.formatString("{0} MaxSize is {1}", "Test Name", 1000000));
        }
        try {
            throw new SmartException(new RuntimeException(new NullPointerException(SmartException.NOT_FOUND)));
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(SmartException.class).hasMessage("java.lang.RuntimeException: java.lang.NullPointerException: NOT_FOUND");
        }
        try {
            throw new SmartException(SmartException.NOT_FOUND, new RuntimeException(new NullPointerException()));
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(SmartException.class).hasMessage("{0} not found");
        }

    }

    @Test
    public void testGetRootCause() throws Exception {
        assertThat(ExceptionUtil.getRootCause(null)).isNull();
        try {
            throw new SmartException(new RuntimeException(new NullPointerException(SmartException.NOT_FOUND)));
        }catch (Throwable t) {
            assertThat(ExceptionUtil.getRootCause(t).getClass()).isEqualTo(NullPointerException.class);
            assertThat(ExceptionUtil.getRootCause(t).getMessage()).isEqualTo("NOT_FOUND");
        }
        try {
            throw new SmartException(new NullPointerException(SmartException.NOT_FOUND));
        }catch (Throwable t) {
            assertThat(ExceptionUtil.getRootCause(t).getClass()).isEqualTo(NullPointerException.class);
            assertThat(ExceptionUtil.getRootCause(t).getMessage()).isEqualTo("NOT_FOUND");
        }
        try {
            throw new SmartException(SmartException.NOT_FOUND, "Kiswani");
        }catch (Throwable t) {
            assertThat(ExceptionUtil.getRootCause(t).getClass()).isEqualTo(SmartException.class);
            assertThat(ExceptionUtil.getRootCause(t).getMessage()).isEqualTo("Kiswani not found");
        }
    }

    @Test
    public void testHandle() throws Exception {
        try {
             ExceptionUtil.handle(null);
        }catch (Throwable t) {
            assertThat(ExceptionUtil.getRootCause(t).getClass()).isEqualTo(IllegalParamException.class);
            assertThat(ExceptionUtil.getRootCause(t).getMessage()).isEqualTo("Exception cant be null");
        }

        ExceptionHandlersFactory.instance().setDefault(new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                exceptionMsg = "Kiswani";
            }
        });
        ExceptionUtil.handle(new NullPointerException());
        assertThat(exceptionMsg).isEqualTo("Kiswani");
        exceptionMsg = "dummyMsg";
    }

}
