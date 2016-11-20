package io.github.rhkiswani.jutils.lang;

import io.github.rhkiswani.jutils.lang.annotations.EqualsField;
import io.github.rhkiswani.jutils.reflection.DefaultReflectionHelper;
import io.github.rhkiswani.jutils.reflection.ReflectionHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

public class EqualsHelper {
    public static boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null){
            return true;
        }
        if (obj1 == null ){
            return false;
        }
        if (obj2 == null){
            return false;
        }
        if (!obj1.getClass().equals(obj2.getClass())){
            return false;
        }
        ReflectionHelper reflectionHelper = new DefaultReflectionHelper();

        List<Field> fields = reflectionHelper.scanFieldsByAnnotation(obj1.getClass(), EqualsField.class);
        if (Arrays.isEmpty(fields)){
            fields = reflectionHelper.scanFieldsByAnnotation(obj1.getClass(), Id.class);
            if (Arrays.isEmpty(fields)){
                fields = reflectionHelper.getFields(obj1.getClass());
            }
        }
        if (fields.size() == 0){
            return obj1.equals(obj2);
        }
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        for (Field field : fields) {
            equalsBuilder.append(reflectionHelper.getFieldValue(obj1, field.getName()), reflectionHelper.getFieldValue(obj2, field.getName()));
        }
        return equalsBuilder.isEquals();
    }
}
