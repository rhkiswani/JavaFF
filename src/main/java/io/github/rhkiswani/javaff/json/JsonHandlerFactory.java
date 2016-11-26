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

import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.factory.AbstractFactory;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.json.annotations.JacksonBean;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.factory.AbstractFactory
 */
public class JsonHandlerFactory extends AbstractFactory<JsonHandler>{

    private static JsonHandlerFactory instance = new JsonHandlerFactory();

    private JsonHandlerFactory(){
        if (ApiDetectorUtil.isGsonAvailable()) {
            add(Object.class, new GsonHandler());
            add(GsonBean.class, new GsonHandler());
        }
        if (ApiDetectorUtil.isJacksonAvailable()) {
            add(JacksonBean.class, new JacksonHandler());
        }
    }

    public static JsonHandlerFactory instance(){
        return instance;
    }

    @Override
    protected String getDefaultImplementationUrl() {
        return "https://mvnrepository.com/artifact/com.google.code.gson/gson";
    }

    public static JsonHandler getJsonHandler(Class aClass) {
        return instance.create(aClass);
    }

}
