package com.rhkiswani.commons.lang;

import com.rhkiswani.commons.format.FormatUtil;
import com.rhkiswani.commons.lang.exceptions.IllegalParamException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysTest {

    @Test
    public void testArraysIsEmpty() {
        Integer[] arr = null;
        assertThat(Arrays.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[0];
        assertThat(Arrays.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, null};
        assertThat(arr.length).isEqualTo(3);
        assertThat(Arrays.isEmpty(arr)).isEqualTo(true);
        arr = new Integer[]{null, null, 1};
        assertThat(Arrays.isEmpty(arr)).isEqualTo(false);
    }

    @Test
    public void testArraysSize() throws Exception {
        Integer[] arr = new Integer[Arrays.MAX_ARRAY_SIZE];
        try {
            Arrays.isEmpty(arr);
        }catch (Throwable t) {
            assertThat(t).isInstanceOf(IllegalParamException.class).hasMessage(FormatUtil.formatString("{0} MaxSize is {1}", "Array", Arrays.MAX_ARRAY_SIZE));
        }
    }

}
