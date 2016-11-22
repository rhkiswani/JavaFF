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

import io.github.rhkiswani.javaff.lang.annotations.EqualsField;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Mohamed Kiswani
 * @email rhkiswani@gmail.com
 * @url https://github.com/rhkiswani
 * @since 0.0.1
 *
 */
public class EqualsHelper extends AbstractObjectHelper<Object[], Boolean>{

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

    public boolean isEqual(Object obj1, Object obj2) {
        return doAction(obj1, obj2);
    }
}
