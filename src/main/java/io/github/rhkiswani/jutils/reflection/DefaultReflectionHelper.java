package io.github.rhkiswani.jutils.reflection;

import io.github.rhkiswani.jutils.exceptions.SmartException;
import io.github.rhkiswani.jutils.reflection.exception.ReflectionException;

import java.lang.reflect.Field;
import java.util.*;

public class DefaultReflectionHelper<T> implements ReflectionHelper<T>{

    @Override
    public List<Field> scanFieldsByAnnotation(Class clazz, Class[] annotations) throws ReflectionException {
        if (clazz == null){
            return null;

        }
        LinkedList<Field> list = new LinkedList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            for (Class aClass : annotations) {
                if (field.isAnnotationPresent(aClass)){
                    list.add(field);
                }
            }
        }
        if (list.size() == 0 && !clazz.getSuperclass().equals(Object.class)){
            return scanFieldsByAnnotation(clazz.getSuperclass(), annotations);
        }
        return list;
    }

    @Override
    public void setFieldValue(T obj, String fieldName, Object value) throws ReflectionException {
        if (obj == null){
            throw new ReflectionException(SmartException.NULL_VAL, obj);
        }
        Field field = getField(obj.getClass(), fieldName);
        if (value != null && !field.getClass().equals(value.getClass())){
            throw new ReflectionException(SmartException.TYPE_ERROR, field.getClass(), value.getClass());
        }
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }

    @Override
    public Field getField(Class clazz, String fieldName) throws ReflectionException {
        if (clazz == null){
            throw new ReflectionException(SmartException.NULL_VAL, clazz);
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)){
                field.setAccessible(true);
                return field;
            }
        }
        if (!clazz.getSuperclass().equals(Object.class)){
             return getField(clazz.getSuperclass(), fieldName);
        }
        throw new ReflectionException(SmartException.NOT_FOUND, fieldName);
    }

    @Override
    public T newInstance(String className, Object... constructorParams) throws ReflectionException {
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    @Override
    public List<Field> getFields(Class clazz) {
        if (clazz == null){
            throw new ReflectionException(SmartException.NULL_VAL, clazz);
        }
        LinkedList<Field> list = new LinkedList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().contains("this$")){
                continue;
            }
            field.setAccessible(true);
            list.add(field);
        }
        if (!clazz.getSuperclass().equals(Object.class)){
            list.addAll(getFields(clazz.getSuperclass()));
        }
        return list;
    }

    public <V> V getFieldValue(T obj, String fieldName) throws ReflectionException {
        try {
            return (V) getField(obj.getClass(), fieldName).get(obj);
        } catch (IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }
}
