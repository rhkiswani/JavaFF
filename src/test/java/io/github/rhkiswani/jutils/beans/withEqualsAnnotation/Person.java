package io.github.rhkiswani.jutils.beans.withEqualsAnnotation;

import io.github.rhkiswani.jutils.beans.ValuesHolder;
import io.github.rhkiswani.jutils.lang.annotations.EqualsField;

public class Person<T> extends ValuesHolder<T> {

    @EqualsField
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
