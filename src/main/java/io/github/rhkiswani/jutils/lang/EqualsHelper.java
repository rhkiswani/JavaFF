package io.github.rhkiswani.jutils.lang;

import io.github.rhkiswani.jutils.lang.annotations.EqualsField;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.lang.reflect.Field;
import java.util.List;

public class EqualsHelper extends AbstractObjectHelper<Object[], Boolean>{

    public static boolean isEqual(Object obj1, Object obj2) {
        return new EqualsHelper().doAction(obj1, obj2);
    }

    @Override
    protected Boolean doAction(Object... objects) {
        if (objects.length < 2 ){
            return false;
        }
        if (objects[0] == null && objects[1] == null){
            return true;
        }
        if (objects[0] == null ){
            return false;
        }
        if (objects[1] == null){
            return false;
        }
        if (!objects[0].getClass().equals(objects[1].getClass())){
            return false;
        }
        List<Field> fields = getFieldsByAnnotation(objects[0], EqualsField.class);
        if (fields.size() == 0){
            return objects[0].equals(objects[1]);
        }
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        for (Field field : fields) {
            equalsBuilder.append(reflectionHelper.getFieldValue(objects[0], field.getName()), reflectionHelper.getFieldValue(objects[1], field.getName()));
        }
        return equalsBuilder.isEquals();
    }
}
