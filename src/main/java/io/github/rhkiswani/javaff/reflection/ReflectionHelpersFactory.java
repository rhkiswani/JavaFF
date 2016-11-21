package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

public class ReflectionHelpersFactory extends AbstractFactory<ReflectionHelper> {
    private static ReflectionHelpersFactory instance = new ReflectionHelpersFactory();

    private ReflectionHelpersFactory(){
    }

    public static ReflectionHelpersFactory instance(){
        return instance;
    }

    @Override
    public ReflectionHelper getDefault(Class targetClazz) {
        return new DefaultReflectionHelper();
    }

}
