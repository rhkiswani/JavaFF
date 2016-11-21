package io.github.rhkiswani.jutils.beans;

import io.github.rhkiswani.jutils.json.JsonHandler;
import io.github.rhkiswani.jutils.json.JsonHandlerFactory;
import io.github.rhkiswani.jutils.lang.EqualsHelper;
import io.github.rhkiswani.jutils.lang.HashCodeHelper;
import io.github.rhkiswani.jutils.lang.ToStringHelper;

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
