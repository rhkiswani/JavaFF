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

import io.github.rhkiswani.javaff.detector.ApiDetectorUtil;
import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;
import io.github.rhkiswani.javaff.reflection.ReflectionHelper;
import io.github.rhkiswani.javaff.reflection.ReflectionHelpersFactory;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public abstract class AbstractObjectHelper<INPUT, OUT> {

    protected final ReflectionHelper reflectionHelper = ReflectionHelpersFactory.getReflectionHelper(this.getClass());

    protected abstract OUT doAction(INPUT input);

    protected List<Field> getFieldsByAnnotation(Object obj,Class annotation){
        return getFieldsByAnnotation(obj, annotation, true);
    }

    protected List<Field> getFieldsByAnnotation(Object obj,Class annotation, boolean returnAllOfNotFound){
        List<Field> fields = reflectionHelper.scanFieldsByAnnotation(obj.getClass(), annotation);
        if (ArraysUtils.isEmpty(fields)){
            if (ApiDetectorUtil.isJPAAvailable()){
                fields = reflectionHelper.scanFieldsByAnnotation(obj.getClass(), Id.class);
            }
            if (ArraysUtils.isEmpty(fields) && returnAllOfNotFound){
                fields = reflectionHelper.getFields(obj.getClass());
            }
        }
        return fields;
    }


}
