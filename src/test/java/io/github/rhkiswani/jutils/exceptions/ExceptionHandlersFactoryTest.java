package io.github.rhkiswani.jutils.exceptions;

import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionHandlersFactoryTest {

    @Test
    public void testRegistry() throws Exception {
        assertThat(ExceptionHandlersFactory.instance()).isNotNull();
        assertThat(ExceptionHandlersFactory.instance() == ExceptionHandlersFactory.instance()).isEqualTo(true);
        assertThat(ExceptionHandlersFactory.instance().listHandlers().size()).isEqualTo(0);
        ExceptionHandlersFactory.instance().add(Throwable.class, new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                t.printStackTrace();
            }
        });
        assertThat(ExceptionHandlersFactory.instance().listHandlers().size()).isEqualTo(1);
        ExceptionHandler exceptionHandler = new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                t.printStackTrace();
            }
        };
        ExceptionHandlersFactory.instance().overrideImp(Throwable.class, exceptionHandler);
        assertThat(ExceptionHandlersFactory.instance().listHandlers().size()).isEqualTo(1);
        ExceptionHandlersFactory.instance().add(SQLException.class, exceptionHandler);
        ExceptionHandlersFactory.instance().overrideImp(SQLException.class, exceptionHandler);
        assertThat(ExceptionHandlersFactory.instance().listHandlers().size()).isEqualTo(2);
        ExceptionHandlersFactory.instance().remove(SQLException.class);
        assertThat(ExceptionHandlersFactory.instance().listHandlers().size()).isEqualTo(1);
    }


}
