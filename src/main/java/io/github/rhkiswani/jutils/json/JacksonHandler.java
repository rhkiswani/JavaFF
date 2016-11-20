package io.github.rhkiswani.jutils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rhkiswani.jutils.json.exceptions.JsonException;

import java.io.IOException;

class JacksonHandler implements JsonHandler {
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T fromJson(String json, Class clazz) {
        try {
            return (T) mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    @Override
    public Object getImplementation() {
        return mapper;
    }
}
