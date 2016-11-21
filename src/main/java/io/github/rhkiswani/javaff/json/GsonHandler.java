package io.github.rhkiswani.javaff.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

class GsonHandler implements JsonHandler {
    private final Gson gson = new GsonBuilder() .excludeFieldsWithModifiers(Modifier.STATIC) .create();


    @Override
    public <T> T fromJson(String json, Class clazz) {
        return (T) gson.fromJson(json, clazz);
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }

    @Override
    public Object getImplementation() {
        return gson;
    }
}
