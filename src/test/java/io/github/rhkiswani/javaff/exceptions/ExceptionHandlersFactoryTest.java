package io.github.rhkiswani.javaff.exceptions;

import io.github.rhkiswani.javaff.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionHandlersFactoryTest {

    private ExceptionHandler exceptionHandler ;

    @Before
    public void setup(){
        exceptionHandler = new TestExceptionHandler();
    }

    @Test
    public void testRegistry() throws Exception {
        assertThat(ExceptionHandlersFactory.instance()).isNotNull();
        assertThat(ExceptionHandlersFactory.instance() == ExceptionHandlersFactory.instance()).isEqualTo(true);
        assertThat(ExceptionHandlersFactory.instance().listImplementations().size()).isEqualTo(1);
        ExceptionHandlersFactory.instance().add(SQLException.class, new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                t.printStackTrace();
            }
        });
        assertThat(ExceptionHandlersFactory.instance().listImplementations().size()).isEqualTo(2);
        try{
            ExceptionHandlersFactory.instance().add(Throwable.class, exceptionHandler);
        }catch (Exception e){
            assertThat(e).isInstanceOf(IllegalParamException.class).hasMessage("class java.lang.Throwable is already exist please use overrideImp method instated");
        }

        ExceptionHandlersFactory.instance().overrideImp(Throwable.class, exceptionHandler);
        assertThat(ExceptionHandlersFactory.instance().listImplementations().size()).isEqualTo(2);
        ExceptionHandlersFactory.instance().add(ArrayIndexOutOfBoundsException.class, exceptionHandler);
        ExceptionHandlersFactory.instance().overrideImp(ArrayIndexOutOfBoundsException.class, exceptionHandler);
        assertThat(ExceptionHandlersFactory.instance().listImplementations().size()).isEqualTo(3);
        ExceptionHandlersFactory.instance().remove(ArrayIndexOutOfBoundsException.class);
        assertThat(ExceptionHandlersFactory.instance().listImplementations().size()).isEqualTo(2);
    }


    @Test
    public void testFactory() throws Exception {
        assertThat(ExceptionHandlersFactory.getExceptionHandler(Throwable.class).getClass()).isEqualTo(DefaultExceptionHandler.class);
        ExceptionHandlersFactory.instance().overrideImp(Throwable.class, exceptionHandler);
        assertThat(ExceptionHandlersFactory.getExceptionHandler(Throwable.class).getClass()).isEqualTo(TestExceptionHandler.class);
        assertThat(ExceptionHandlersFactory.getExceptionHandler(SQLException.class).getClass()).isEqualTo(TestExceptionHandler.class);
        assertThat(ExceptionHandlersFactory.getExceptionHandler(EmployeeX.class).getClass()).isEqualTo(DefaultExceptionHandler.class);

        try {
            ExceptionHandlersFactory.getExceptionHandler(null);
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalParamException.class).hasMessage("Target Class cant be null");
        }
    }

    private class TestExceptionHandler implements ExceptionHandler {
        @Override
        public void handle(Throwable t) {
            t.printStackTrace();
        }
    };
}
