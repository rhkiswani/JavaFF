package io.github.rhkiswani.jutils.decetor;

import io.github.rhkiswani.jutils.reflection.ReflectionUtil;

class DefaultApiDetectorHandler implements ApiDetector{
    public boolean isAvailable(String keyClassName){
        return ReflectionUtil.isPresent(keyClassName);
    }
}
