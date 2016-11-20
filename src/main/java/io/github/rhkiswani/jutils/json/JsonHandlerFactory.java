package io.github.rhkiswani.jutils.json;

import io.github.rhkiswani.jutils.reflection.ReflectionUtil;

public class JsonHandlerFactory {

    private JsonHandlerFactory() {
        // Do nothing
    }

    public static JsonHandler getHandler() {
        if (ReflectionUtil.isPresent("com.fasterxml.jackson.databind.ObjectMapper")) {
            return new JacksonHandler();
        } else {
            return new GsonHandler();
        }
    }
}
