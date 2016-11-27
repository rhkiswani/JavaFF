/*
 * Copyright 2016 Mohamed Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rhkiswani.javaff.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.rhkiswani.javaff.json.exceptions.JsonException;

import java.io.IOException;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.json.JsonHandler
 */
class JacksonHandler implements JsonHandler {
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T fromJson(String json, Class clazz) {
        try {
            return (T) mapper.readValue(json, clazz);
        } catch (Throwable e) {
            throw new JsonException(e.getMessage());
        }
    }

    @Override
    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Throwable e) {
            throw new JsonException(e.getMessage());
        }
    }

    @Override
    public Object getImplementation() {
        return mapper;
    }
}
