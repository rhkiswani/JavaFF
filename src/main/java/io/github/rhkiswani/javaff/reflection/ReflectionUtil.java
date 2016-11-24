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
package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.reflection.exception.ReflectionException;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class ReflectionUtil {

    private ReflectionUtil(){


    }

    public static boolean isPresent(String className) {
        return isPresent(className, Thread.currentThread().getContextClassLoader());
    }

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            classLoader.loadClass(className);
            return true;
        } catch (Throwable ex) {
            // Class or one of its dependencies is not present...
            return false;
        }
    }

    public static void setFieldValue(Object obj, String fieldName, Object val) {
        new DefaultReflectionHelper().setFieldValue(obj, fieldName, val);
    }

    public static Class getCallerClass(int numberOfLevels){
        StackTraceElement[] stackTraceElements = new Exception().getStackTrace();
        if (numberOfLevels < 0){
            throw new IllegalParamException(SmartException.NEGATIVE_VAL, "Number Of Levels");
        }
        if (numberOfLevels + 1 > stackTraceElements.length){
            throw new IllegalParamException(SmartException.EXCEEDS_LIMIT, "StackTrace", stackTraceElements.length);
        }
        return forName(stackTraceElements[numberOfLevels+1].getClassName());
    }

    public static Class forName(String className) {
        try{
            return Class.forName(className);
        } catch (Exception e){
            throw new ReflectionException(e);
        }
    }
}
