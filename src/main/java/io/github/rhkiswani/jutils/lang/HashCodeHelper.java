package io.github.rhkiswani.jutils.lang;

import io.github.rhkiswani.jutils.lang.annotations.HashcodeField;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;

public class HashCodeHelper extends AbstractObjectHelper<Object, Integer>{

    public static int toHashCode(Object obj) {
        return new HashCodeHelper().doAction(obj);
    }

    protected Integer doAction(Object obj) {
        if (obj == null ){
            return -1;
        }
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        for (Field field : getFieldsByAnnotation(obj, HashcodeField.class)) {
            hashCodeBuilder.append(reflectionHelper.getFieldValue(obj, field.getName()));
        }
        return hashCodeBuilder.toHashCode();
    }
}
