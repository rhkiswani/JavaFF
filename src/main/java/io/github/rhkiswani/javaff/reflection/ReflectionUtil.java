package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.reflection.exception.ReflectionException;

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
            throw new IllegalParamException(SmartException.NIGATIVE_VAL, "Number Of Levels");
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
