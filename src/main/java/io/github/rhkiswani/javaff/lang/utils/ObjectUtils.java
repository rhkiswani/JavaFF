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
import io.github.rhkiswani.javaff.lang.EqualsHelper;
import io.github.rhkiswani.javaff.lang.HashCodeHelper;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class ObjectUtils {

    public static boolean isEqual(Object obj1, Object obj2) {
        return new EqualsHelper().isEqual(obj1, obj2);
    }

    public static int toHashCode(Object obj) {
        return new HashCodeHelper().toHashCode(obj);
    }

    public static Class primitiveToWrapper(Class targetClass) {
        if (targetClass == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "Target Class");
        }
        if (!targetClass.isPrimitive()){
            throw new IllegalParamException("{0} is not primitive", targetClass.getSimpleName());
        }
        if (targetClass.equals(byte.class)){
            return Byte.class;
        } else if (targetClass.equals(char.class)) {
            return Character.class;
        } else if (targetClass.equals(short.class)) {
            return Short.class;
        } else if (targetClass.equals(int.class)) {
            return Integer.class;
        } else if (targetClass.equals(long.class)) {
            return Long.class;
        } else if (targetClass.equals(float.class)) {
            return Float.class;
        } else if (targetClass.equals(double.class)) {
            return Double.class;
        } else if (targetClass.equals(boolean.class)) {
            return Boolean.class;
        }
        return targetClass;
    }
}
