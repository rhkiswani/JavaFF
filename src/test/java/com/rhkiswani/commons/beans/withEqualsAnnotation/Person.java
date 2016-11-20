package com.rhkiswani.commons.beans.withEqualsAnnotation;

import com.rhkiswani.commons.beans.ValuesHolder;
import com.rhkiswani.commons.lang.annotations.EqualsField;

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
