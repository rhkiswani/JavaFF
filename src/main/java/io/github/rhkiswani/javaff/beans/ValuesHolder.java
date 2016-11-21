package io.github.rhkiswani.javaff.beans;

import io.github.rhkiswani.javaff.json.JsonHandler;
import io.github.rhkiswani.javaff.json.JsonHandlerFactory;
import io.github.rhkiswani.javaff.lang.EqualsHelper;
import io.github.rhkiswani.javaff.lang.HashCodeHelper;
import io.github.rhkiswani.javaff.lang.ToStringHelper;

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
        return ToStringHelper.toString(this);
    }

    public T clone() {
        JsonHandler jh = JsonHandlerFactory.instance().getHandlerFor(this.getClass());
        String json = jh.toJson(this);
        return (T) jh.fromJson(json, getClass());
    }
}
