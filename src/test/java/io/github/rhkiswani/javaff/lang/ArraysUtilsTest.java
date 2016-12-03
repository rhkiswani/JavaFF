package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.lang.utils.*;
import io.github.rhkiswani.javaff.locale.LocaleUtil;
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

    @Test
    public void testReplace() throws Exception {
        new ArraysUtils();
        new ObjectUtils();
        new StringUtils();
        new LocaleUtil();// for coverage
        Integer[] arr = {1, 2 , 4};
        String[] strings = {null};
        ArraysUtils.replace(strings, null, "1");
        assertThat(strings[0]).isNull();
        ArraysUtils.replace(arr, 1, 4);
        assertThat(arr[0]).isEqualTo(4);
        ArraysUtils.replace(arr, 4, null);
        assertThat(arr[0]).isNull();
        assertThat(arr[2]).isNull();
    }

}
