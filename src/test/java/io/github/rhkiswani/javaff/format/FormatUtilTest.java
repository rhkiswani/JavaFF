package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.beans.EmployeeX;
import org.assertj.core.util.Arrays;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatUtilTest extends AbstractFormatTest<String>{

    @Test
    public void testTextFormatter() {
        assertThat(FormatUtil.format(null, null)).isNull();
        assertThat(FormatUtil.format("Kiswani {0}", Arrays.array("", "1"))).isEqualTo("Kiswani ");
        assertThat(FormatUtil.format("Kiswani {0}", null)).isEqualTo("Kiswani {0}");
        assertThat(FormatUtil.format("Kiswani {0} {1}", Arrays.array(Arrays.array(1, 2 , 3)))).isEqualTo("Kiswani 1 2");
        assertThat(FormatUtil.format("Kiswani {0} {1}", Arrays.array(Arrays.array(Arrays.array(1, 2 , 3))))).isEqualTo("Kiswani 1 2");
    }

    @Test
    public void testDefault() {
        assertThat(FormatFactory.getFormatter(EmployeeX.class)).isInstanceOf(StringFormatter.class);
    }
}
