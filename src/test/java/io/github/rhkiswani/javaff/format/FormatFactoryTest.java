package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.format.exception.FormatException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatFactoryTest {

    @Test
    public void testRegistry() throws Exception {
        assertThat(FormatFactory.instance()).isNotNull();
        assertThat(FormatFactory.instance() == FormatFactory.instance()).isEqualTo(true);
        assertThat(FormatFactory.instance().listImplementations().size()).isEqualTo(3);
        FormatFactory.instance().add(Character.class, new Formatter<Character,String>() {

            @Override
            protected String format(Character in, Object... params) throws FormatException {
                return FormatUtil.format(in, params);
            }
        });
        assertThat(FormatFactory.instance().listImplementations().size()).isEqualTo(4);
    }
}
