package io.github.rhkiswani.jutils.json;

import io.github.rhkiswani.jutils.decetor.ApiDetectorUtil;
import io.github.rhkiswani.jutils.factory.AbstractFactory;
import io.github.rhkiswani.jutils.json.annotations.GsonBean;
import io.github.rhkiswani.jutils.json.annotations.JacksonBean;

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
