package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.annotations.EqualsField;
import io.github.rhkiswani.javaff.security.encode.EncodeFactory;

import java.lang.reflect.Field;
import java.util.List;

public class ToStringHelper extends AbstractObjectHelper<Object, String>{

    @Override
    protected String doAction(Object obj) {
        List<Field> fields = getFieldsByAnnotation(obj, EqualsField.class);
        StringBuilder builder = new StringBuilder();
        builder.append(obj.getClass().getSimpleName());
        builder.append("[");
        Object[] values = new Object[fields.size()];
        for (int i = 0; i < fields.size() ; i++) {
            Field f = fields.get(i);
            builder.append(f.getName());
            builder.append("=");
            builder.append("{"+i+"}");
            values[i]= reflectionHelper.getFieldValue(obj, f.getName());
            if ( i < fields.size() -1){
                builder.append(", ");
            }
        }
        builder.append("]");
        return FormatUtil.formatString(builder.toString(), values);
    }

    public static String toString(Object obj) {
        return new ToStringHelper().doAction(obj);
    }

    public static String encode(String input) {
        return (String) EncodeFactory.instance().getHandlerFor(String.class).encode(input);
    }
}
