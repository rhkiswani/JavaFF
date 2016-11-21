package io.github.rhkiswani.javaff.decetor;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

public class ApiDetectorFactory extends AbstractFactory<ApiDetector>{
    private static ApiDetectorFactory instance = new ApiDetectorFactory();

    private ApiDetectorFactory(){
    }

    public static ApiDetectorFactory instance(){
        return instance;
    }

    @Override
    public ApiDetector getDefault(Class targetClazz) {
        return new DefaultApiDetectorHandler();
    }
}
