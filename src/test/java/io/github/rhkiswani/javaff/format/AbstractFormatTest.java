package io.github.rhkiswani.javaff.format;

import org.assertj.core.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractFormatTest<T> {

    public void testNulls(Formatter formatter, T value) {
        assertThat(formatter.format(null, Arrays.array(null))).isNull();
        assertThat(formatter.format(null, Arrays.array(null, null))).isNull();
        assertThat(formatter.format(value, null)).isNotNull();
    }


}
