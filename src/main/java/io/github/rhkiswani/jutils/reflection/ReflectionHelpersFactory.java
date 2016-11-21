package io.github.rhkiswani.jutils.reflection;

import io.github.rhkiswani.jutils.factory.AbstractFactory;

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
