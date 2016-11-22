package io.github.rhkiswani.javaff.format;

import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class TextFormatTest {

    @Test
    public void testTextFormatter() {
        assertThat(new StringFormatter().format(null, Arrays.array(null))).isNull();
        assertThat(new StringFormatter().format(null, Arrays.array(null, null))).isNull();
        assertThat(new StringFormatter().format("Kiswani", null)).isEqualTo("Kiswani");
        assertThat(new StringFormatter().format("Kiswani", Arrays.array(3))).isEqualTo("Kiswani");
        assertThat(new StringFormatter().format("Kiswani {1}", Arrays.array(null, "1"))).isEqualTo("Kiswani 1");
        assertThat(new StringFormatter().format("Kiswani {0}", Arrays.array(null, "1"))).isEqualTo("Kiswani ");
        assertThat(new StringFormatter().format("Kiswani {0}", Arrays.array("", "1"))).isEqualTo("Kiswani ");
        assertThat(new StringFormatter().format("Kiswani {0}", null)).isEqualTo("Kiswani {0}");
        assertThat(new StringFormatter().format("Kiswani {0} {1}", Arrays.array(Arrays.array(1, 2 , 3)))).isEqualTo("Kiswani 1 2");
        assertThat(new StringFormatter().format("Kiswani {0} {1}", Arrays.array(Arrays.array(Arrays.array(1, 2 , 3))))).isEqualTo("Kiswani 1 2");

    }

}
