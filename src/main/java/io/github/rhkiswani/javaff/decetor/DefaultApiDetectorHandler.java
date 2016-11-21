package io.github.rhkiswani.javaff.decetor;

import io.github.rhkiswani.javaff.reflection.ReflectionUtil;

class DefaultApiDetectorHandler implements ApiDetector{
    public boolean isAvailable(String keyClassName){
        return ReflectionUtil.isPresent(keyClassName);
    }
}
