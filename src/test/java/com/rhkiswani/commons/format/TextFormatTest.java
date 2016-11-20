package com.rhkiswani.commons.format;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextFormatTest {

    @Test
    public void testTextFormatter() {
        assertThat(new StringFormatter().format(null)).isNull();
        assertThat(new StringFormatter().format(null, null, null)).isNull();
        assertThat(new StringFormatter().format("Kiswani")).isEqualTo("Kiswani");
        assertThat(new StringFormatter().format("Kiswani", 3)).isEqualTo("Kiswani");
        assertThat(new StringFormatter().format("Kiswani {1}", null, "1")).isEqualTo("Kiswani 1");
        assertThat(new StringFormatter().format("Kiswani {0}", null, "1")).isEqualTo("Kiswani ");
        assertThat(new StringFormatter().format("Kiswani {0}", "", "1")).isEqualTo("Kiswani ");
        assertThat(new StringFormatter().format("Kiswani {0}")).isEqualTo("Kiswani {0}");
    }

}
