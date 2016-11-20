package com.rhkiswani.commons.reflection;

import com.rhkiswani.commons.reflection.exception.ReflectionException;

import java.lang.reflect.Field;
import java.util.List;

public interface ReflectionHelper<T> {

    List<Field> scanFieldsByAnnotation(Class clazz, Class... annotations) throws ReflectionException;

    void setFieldValue(T obj,String fieldName, Object value) throws ReflectionException;

    Field getField(Class clazz, String fieldName) throws ReflectionException;

    <V> V getFieldValue(T obj, String fieldName) throws ReflectionException;

    T newInstance(String className, Object... constructorParams) throws ReflectionException;

    List<Field> getFields(Class clazz);
}
