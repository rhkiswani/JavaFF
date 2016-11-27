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
package io.github.rhkiswani.javaff.reflection;

import io.github.rhkiswani.javaff.reflection.exception.ReflectionException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public interface ReflectionHelper<T> {

    List<Field> scanFieldsByAnnotation(Class clazz, Class... annotations) throws ReflectionException;

    void setFieldValue(T obj,String fieldName, Object value) throws ReflectionException;

    Field getField(Class clazz, String fieldName) throws ReflectionException;

    <V> V getFieldValue(T obj, String fieldName) throws ReflectionException;

    Class forName(String className) throws ReflectionException;

    List<Field> getFields(Class clazz);
}
