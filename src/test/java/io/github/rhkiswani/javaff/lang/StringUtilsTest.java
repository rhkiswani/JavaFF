package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.lang.utils.StringUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class StringUtilsTest {

    @Test
    public void testStringUtils() throws Exception {
        assertThat(StringUtils.escape(null)).isEqualTo(null);
        assertThat(StringUtils.escape("Kiswani")).isEqualTo("Kiswani");
        assertThat(StringUtils.escape("rhkiswani@gmail.com")).isEqualTo("rhkiswani%40gmail%2Ecom");
        assertThat(StringUtils.escape("%$&+.,/@:;=?<>#%")).isEqualTo("%25%24%26%2B%2E%2C%2F%40%3A%3B%3D%3F%3C%3E%23%25");
    }

}
