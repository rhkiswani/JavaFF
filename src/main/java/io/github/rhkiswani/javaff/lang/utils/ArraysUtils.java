/*
 * Copyright 2016 Mohamed Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rhkiswani.javaff.lang.utils;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * @author Mohamed Kiswani
 * @email rhkiswani@gmail.com
 * @url https://github.com/rhkiswani
 * @since 0.0.1
 *
 */
public class ArraysUtils {

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
