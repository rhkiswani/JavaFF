package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysUtilsTest {

    @Test
    public void testArraysIsEmpty() {
        Integer[] arr = null;
        assertThat(ArraysUtils.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[0];
        assertThat(ArraysUtils.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, null};
        assertThat(arr.length).isEqualTo(3);
        assertThat(ArraysUtils.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, 1};
        assertThat(ArraysUtils.isEmpty(arr)).isEqualTo(false);
    }

    @Test
    public void testArraysSize() throws Exception {
        Integer[] arr = new Integer[ArraysUtils.MAX_ARRAY_SIZE];
        try {
            ArraysUtils.isEmpty(arr);
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalParamException.class).hasMessage(FormatUtil.formatString("{0} MaxSize is {1}", "Array", ArraysUtils.MAX_ARRAY_SIZE));
        }
    }

}
