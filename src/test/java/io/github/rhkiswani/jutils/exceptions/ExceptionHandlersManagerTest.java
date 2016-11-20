package io.github.rhkiswani.jutils.exceptions;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionHandlersManagerTest {

    @Test
    public void testRegistry() throws Exception {
        assertThat(ExceptionHandlersManager.instance()).isNotNull();
        assertThat(ExceptionHandlersManager.instance() == ExceptionHandlersManager.instance()).isEqualTo(true);
        assertThat(ExceptionHandlersManager.instance().listHandlers().size()).isEqualTo(1);
        ExceptionHandlersManager.instance().add(new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                t.printStackTrace();
            }
        });
        assertThat(ExceptionHandlersManager.instance().listHandlers().size()).isEqualTo(2);
        ExceptionHandler exceptionHandler = new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                t.printStackTrace();
            }
        };
        ExceptionHandlersManager.instance().add(exceptionHandler);
        assertThat(ExceptionHandlersManager.instance().listHandlers().size()).isEqualTo(3);
        ExceptionHandlersManager.instance().add(exceptionHandler);
        ExceptionHandlersManager.instance().add(exceptionHandler);
        assertThat(ExceptionHandlersManager.instance().listHandlers().size()).isEqualTo(3);
        ExceptionHandlersManager.instance().remove(exceptionHandler);
        assertThat(ExceptionHandlersManager.instance().listHandlers().size()).isEqualTo(2);
    }


}
