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

import io.github.rhkiswani.javaff.lang.ToStringHelper;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class StringUtils {

    public static String toString(Object obj) {
        return new ToStringHelper().toString(obj);
    }

    public static boolean isEmpty(String input){
        return input == null || input.isEmpty();
    }

    public static boolean isNotEmpty(String input){
        return !isEmpty(input);
    }

    public static boolean isNull(String input){
        return isEmpty(input) || "NULL".equalsIgnoreCase(input);
    }

    public static boolean isNotNull(String input){
        return !isNull(input);
    }
}
