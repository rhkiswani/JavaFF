package com.rhkiswani.commons.reflection;

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
}
