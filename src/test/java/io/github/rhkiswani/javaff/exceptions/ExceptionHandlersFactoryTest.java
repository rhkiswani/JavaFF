package io.github.rhkiswani.javaff.exceptions;

import io.github.rhkiswani.javaff.beans.withOutAnnotation.withEqualsAnnotation.EmployeeX;
import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
        //FIXME it's failing on travis but not local
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
                exceptionMsg = "userDefaultHandler";
            }
        });
        ExceptionUtil.handle(new NullPointerException());
        assertThat(exceptionMsg).isEqualTo("userDefaultHandler");

        //Any class is instance of ConsoleException will be handled here
        ExceptionHandlersFactory.instance().add(ConsoleException.class, new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                exceptionMsg = "ConsoleException handler";
            }
        });

        //Any class is instance of MailException will be handled here
        ExceptionHandlersFactory.instance().add(MailException.class, new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                exceptionMsg = "MailException handler";
            }
        });


        ExceptionUtil.handle(new ConsoleException());
        assertThat(exceptionMsg).isEqualTo("userDefaultHandler");
        ExceptionHandlersFactory.instance().setDefault(null);
        ExceptionUtil.handle(new ConsoleException());
        assertThat(exceptionMsg).isEqualTo("ConsoleException handler");
        ExceptionUtil.handle(new SubConsoleException());
        assertThat(exceptionMsg).isEqualTo("ConsoleException handler");
        ExceptionUtil.handle(new MailException());
        assertThat(exceptionMsg).isEqualTo("MailException handler");
        ExceptionUtil.handle(new SubMailException());
        assertThat(exceptionMsg).isEqualTo("MailException handler");

        //We decided to override the default implantation for Throwable.class
        ExceptionHandlersFactory.instance().overrideImp(Throwable.class, new ExceptionHandler() {
            @Override
            public void handle(Throwable t) {
                exceptionMsg = "Overridden handler";
            }
        });

        ExceptionUtil.handle(new NullPointerException());
        assertThat(exceptionMsg).isEqualTo("Overridden handler");
        ExceptionHandlersFactory.instance().remove(MailException.class);
        ExceptionHandlersFactory.instance().remove(ConsoleException.class);
    }

    private static class ConsoleException extends RuntimeException{

    }

    private static class SubConsoleException extends ConsoleException{

    }

    private static class MailException extends RuntimeException{

    }

    private static class SubMailException extends MailException {

    }
}
