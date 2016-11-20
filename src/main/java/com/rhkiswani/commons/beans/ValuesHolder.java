package com.rhkiswani.commons.beans;

import com.rhkiswani.commons.json.JsonHandler;
import com.rhkiswani.commons.json.JsonHandlerFactory;
import com.rhkiswani.commons.lang.EqualsHelper;
import com.rhkiswani.commons.lang.HashCodeHelper;
import com.rhkiswani.commons.lang.StringHelper;

import java.io.Serializable;

public class ValuesHolder<T> implements Serializable{

    @Override
    public boolean equals(Object obj) {
        return EqualsHelper.isEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeHelper.toHashCode(this);
    }

    @Override
    public String toString() {
        return StringHelper.toString(this);
    }

    public T clone() {
        JsonHandler jh =JsonHandlerFactory.getHandler();
        String json = jh.toJson(this);
        return (T) jh.fromJson(json, getClass());
    }
}
