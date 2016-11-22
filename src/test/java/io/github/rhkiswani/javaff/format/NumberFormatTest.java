package io.github.rhkiswani.javaff.format;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberFormatTest extends AbstractFormatTest<Number>{

    @Test
    public void testNumberFormatter() {
        testNulls(new NumberFormatter(), 10000);

        assertThat(new NumberFormatter().format(Float.MAX_VALUE / Math.pow(10, 10))).isEqualTo("34,028,234,663,852,887,000,000,000,000");
        assertThat(new NumberFormatter().format(Double.MAX_VALUE / Math.pow(10, 300))).isEqualTo("179,769,313.486");
        assertThat(new NumberFormatter().format(Long.MAX_VALUE / Math.pow(10, 10))).isEqualTo("922,337,203.685");
        assertThat(new NumberFormatter().format(Integer.MAX_VALUE)).isEqualTo("2,147,483,647");
        assertThat(new NumberFormatter().format(Short.MAX_VALUE)).isEqualTo("32,767");
    }

}
