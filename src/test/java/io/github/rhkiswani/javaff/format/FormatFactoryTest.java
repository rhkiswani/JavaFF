package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.exceptions.ExceptionHandler;
import io.github.rhkiswani.javaff.exceptions.ExceptionHandlersFactory;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatFactoryTest {

    private ExceptionHandler exceptionHandler ;

    @Before
    public void setup(){
        exceptionHandler = new TestExceptionHandler();
    }

    @Test
    public void testRegistry() throws Exception {
        assertThat(FormatFactory.instance()).isNotNull();
        assertThat(FormatFactory.instance() == FormatFactory.instance()).isEqualTo(true);
        assertThat(FormatFactory.instance().listImplementations().size()).isEqualTo(3);
        FormatFactory.instance().add(Character.class, new DefaultFormatter<Character,String>() {
            @Override
            protected String formatVal(Character character, Object[] params) {
                return character.toString();
            }

        });
        assertThat(FormatFactory.instance().listImplementations().size()).isEqualTo(4);
    }


    private class TestExceptionHandler implements ExceptionHandler {
        @Override
        public void handle(Throwable t) {
            t.printStackTrace();
        }
    };
}
