/*
 * Copyright 2016 Mohamed Kiswani.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.rhkiswani.javaff.lang;

import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.json.JsonHandlerFactory;
import io.github.rhkiswani.javaff.json.annotations.GsonBean;
import io.github.rhkiswani.javaff.lang.annotations.EqualsField;
import io.github.rhkiswani.javaff.lang.annotations.HashcodeField;
import io.github.rhkiswani.javaff.lang.annotations.ToString;
import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.lang.AbstractObjectHelper
 */
public class ToStringHelper extends AbstractObjectHelper<Object, String>{

    @Override
    protected String doAction(Object obj) {
        if (obj == null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(obj.getClass().getSimpleName());
        builder.append("[");
        builder.append(normalToString(obj));
        builder.append("]");
        return builder.toString();
    }

    private String gosnToString(Object obj) {
        String json = JsonHandlerFactory.getJsonHandler(GsonBean.class).toJson(obj);
        json = json.replace("{", "[");
        json = json.replace("}", "]");
        json = json.replace("\"", "");
        return json;
    }

    private String normalToString(Object obj) {
        StringBuilder builder = new StringBuilder();
        List<Field> fields = getFieldsByAnnotation(obj, ToString.class, false);
        if (ArraysUtils.isEmpty(fields)){
            fields = getFieldsByAnnotation(obj, EqualsField.class, false);
        }
        if (ArraysUtils.isEmpty(fields)){
            fields = getFieldsByAnnotation(obj, HashcodeField.class, true);
        }
        for (int i = 0; i < fields.size() ; i++) {
            Field f = fields.get(i);
            Object fieldValue = reflectionHelper.getFieldValue(obj, f.getName());
            if (fieldValue == null){
                continue;
            }
            builder.append(f.getName());
            builder.append("=");
            if ((fieldValue.getClass().isArray() || Collection.class.isInstance(fieldValue))){
                builder.append(gosnToString(fieldValue));
            } else {
                builder.append(FormatUtil.format("{0}", fieldValue));
            }
            if ( i < fields.size() -1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public String toString(Object obj) {
        return doAction(obj);
    }
}
