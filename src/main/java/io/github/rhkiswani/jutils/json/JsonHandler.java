package io.github.rhkiswani.jutils.json;

public interface JsonHandler {

    <T> T fromJson(String json, Class clazz);

    String toJson(Object object);

    Object getImplementation();

}
