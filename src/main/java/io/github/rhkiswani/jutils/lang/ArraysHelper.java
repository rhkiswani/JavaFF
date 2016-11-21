package io.github.rhkiswani.jutils.lang;

import io.github.rhkiswani.jutils.exceptions.SmartException;
import io.github.rhkiswani.jutils.lang.exceptions.IllegalParamException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class ArraysHelper {

    public static final int MAX_ARRAY_SIZE = 500000;

    public static boolean isEmpty(Object[] arr) {
        if (arr == null){
            return true;
        }
        if (arr.length == 0){
            return true;
        }
        if (arr.length >= MAX_ARRAY_SIZE){
            throw new IllegalParamException(SmartException.EXCEEDS_LIMIT, "Array", 500000);
        }
        return ObjectUtils.firstNonNull(arr) == null;
    }

    public static boolean isEmpty(List list) {
        return isEmpty(list.toArray());
    }

    public static void replace(Object[] arr, Object find, Object replace) {
        if (isEmpty(arr)){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] == null && find == null ) || (arr[i].equals(find))) {
                arr[i] = replace;
            }
        }
    }
}
