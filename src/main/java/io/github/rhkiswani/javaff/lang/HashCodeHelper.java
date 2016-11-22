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

import io.github.rhkiswani.javaff.lang.annotations.HashcodeField;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.lang.AbstractObjectHelper
 */
public class HashCodeHelper extends AbstractObjectHelper<Object, Integer>{

    protected Integer doAction(Object obj) {
        if (obj == null ){
            return -1;
        }
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        boolean atLestOneValueInserted = false;
        for (Field field : getFieldsByAnnotation(obj, HashcodeField.class)) {
            Object val = reflectionHelper.getFieldValue(obj, field.getName());
            if (val != null){
                atLestOneValueInserted = true;
                hashCodeBuilder.append(val);
            }

        }
        return atLestOneValueInserted ? hashCodeBuilder.toHashCode() : -1;
    }

    public int toHashCode(Object obj) {
        return doAction(obj);
    }
}
