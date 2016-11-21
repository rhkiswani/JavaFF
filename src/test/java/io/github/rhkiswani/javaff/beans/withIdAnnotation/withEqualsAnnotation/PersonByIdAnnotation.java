package io.github.rhkiswani.javaff.beans.withIdAnnotation.withEqualsAnnotation;

import io.github.rhkiswani.javaff.beans.ValuesHolder;
import io.github.rhkiswani.javaff.lang.annotations.HashcodeField;

import javax.persistence.Id;

public class PersonByIdAnnotation<T> extends ValuesHolder<T> {

    @Id
    @HashcodeField
    private int id;
    @HashcodeField
    private String name;

    private transient String transientVal;
    public static String staticVal;

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


    public String getTransientVal() {
        return transientVal;
    }

    public void setTransientVal(String transientVal) {
        this.transientVal = transientVal;
    }
}
