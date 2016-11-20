package com.rhkiswani.commons.lang;

import com.rhkiswani.commons.lang.annotations.HashcodeField;
import com.rhkiswani.commons.reflection.DefaultReflectionHelper;
import com.rhkiswani.commons.reflection.ReflectionHelper;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

public class HashCodeHelper {
    public static int toHashCode(Object obj) {
        if (obj == null ){
            return -1;
        }
        ReflectionHelper reflectionHelper = new DefaultReflectionHelper();

        List<Field> fields = reflectionHelper.scanFieldsByAnnotation(obj.getClass(), HashcodeField.class);
        if (Arrays.isEmpty(fields)){
            fields = reflectionHelper.scanFieldsByAnnotation(obj.getClass(), Id.class);
            if (Arrays.isEmpty(fields)){
                fields = reflectionHelper.getFields(obj.getClass());
            }
        }
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        for (Field field : fields) {
            hashCodeBuilder.append(reflectionHelper.getFieldValue(obj, field.getName()));
        }
        return hashCodeBuilder.toHashCode();
    }
}
