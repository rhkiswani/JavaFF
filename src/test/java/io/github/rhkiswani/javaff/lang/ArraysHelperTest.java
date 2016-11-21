package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysHelperTest {

    @Test
    public void testArraysIsEmpty() {
        Integer[] arr = null;
        assertThat(ArraysHelper.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[0];
        assertThat(ArraysHelper.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, null};
        assertThat(arr.length).isEqualTo(3);
        assertThat(ArraysHelper.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, 1};
        assertThat(ArraysHelper.isEmpty(arr)).isEqualTo(false);
    }

    @Test
    public void testArraysSize() throws Exception {
        Integer[] arr = new Integer[ArraysHelper.MAX_ARRAY_SIZE];
        try {
            ArraysHelper.isEmpty(arr);
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalParamException.class).hasMessage(FormatUtil.formatString("{0} MaxSize is {1}", "Array", ArraysHelper.MAX_ARRAY_SIZE));
        }
    }

}
