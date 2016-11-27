package io.github.rhkiswani.javaff.format;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatFactoryTest {

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
}
