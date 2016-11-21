package io.github.rhkiswani.javaff.json;

import io.github.rhkiswani.javaff.decetor.ApiDetectorUtil;
import io.github.rhkiswani.javaff.factory.AbstractFactory;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.json.annotations.JacksonBean;

public class JsonHandlerFactory extends AbstractFactory<JsonHandler>{

    private static JsonHandlerFactory instance = new JsonHandlerFactory();

    private JsonHandlerFactory(){
        add(JacksonBean.class, new JacksonHandler());
        add(GsonBean.class, new GsonHandler());
    }

    public static JsonHandlerFactory instance(){
        return instance;
    }

    @Override
    public JsonHandler getDefault(Class targetClazz) {
        if (ApiDetectorUtil.isJacksonAvailable()){
            return new JacksonHandler();
        }
        return new GsonHandler();
    }
}
