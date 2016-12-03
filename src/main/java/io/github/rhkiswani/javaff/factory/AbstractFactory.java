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
package io.github.rhkiswani.javaff.factory;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.factory.exceptions.NoImplementationFoundException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;
import io.github.rhkiswani.javaff.lang.utils.StringUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public abstract class AbstractFactory<T> {

    private Map<Class, T> map = new LinkedHashMap<>();

    private T userDefaultImpl = null;

    public void add(Class targetClass, T t){
        if (map.get(targetClass) != null){
            throw new IllegalParamException(SmartException.ALREADY_EXIST, targetClass, "please use overrideImp method instated");
        }
        if (targetClass.isPrimitive()){
            targetClass = toWrapper(targetClass);
        }
        map.put(targetClass, t);
    }

    public void overrideImp(Class targetClass, T t){
        if (targetClass.isPrimitive()){
            targetClass = toWrapper(targetClass);
        }
        map.put(targetClass, t);
    }

    public T remove(Class targetClass){
        if (targetClass.isPrimitive()){
            targetClass = toWrapper(targetClass);
        }
        return map.remove(targetClass);
    }

    protected T create(Class clazz){
        if (clazz == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "Target Class");
        }
        if (userDefaultImpl != null){
            return userDefaultImpl;
        }
        Class targetClass = clazz;
        if (targetClass.isPrimitive()){
            targetClass = toWrapper(targetClass);
        }
        Set<Class> classSet = map.keySet();
        Class[] keys = classSet.toArray(new Class[classSet.size()]);
        for (int i = keys.length - 1 ; i >=0 ; i--){
            Class aClass = keys[i];
            if (aClass.isAssignableFrom(targetClass) || targetClass.isAnnotationPresent(aClass)){
                return map.get(aClass);
            }
        }
        return getDefault(targetClass);
    }

    private Class toWrapper(Class targetClass) {
        if (targetClass.equals(byte.class)){
            return Byte.class;
        } else if (targetClass.equals(char.class)) {
            return Character.class;
        } else if (targetClass.equals(short.class)) {
            return Short.class;
        } else if (targetClass.equals(int.class)) {
            return Integer.class;
        } else if (targetClass.equals(long.class)) {
            return Long.class;
        } else if (targetClass.equals(float.class)) {
            return Float.class;
        } else if (targetClass.equals(double.class)) {
            return Double.class;
        } else if (targetClass.equals(boolean.class)) {
            return Boolean.class;
        }
        return targetClass;
    }

    public Collection<T> listImplementations(){
        return map.values();
    }

    public void setDefault(T userDefaultImpl) {
        this.userDefaultImpl = userDefaultImpl;
    }

    protected T getDefault(Class targetClazz) {
        if (StringUtils.isNotEmpty(getDefaultImplementationUrl())){
            throw new NoImplementationFoundException(SmartException.NO_IMPLEMENTATION_FOUND, targetClazz, this.getClass().getSimpleName(), getDefaultImplementationUrl());
        } else {
            throw new NoImplementationFoundException(SmartException.NO_IMPLEMENTATION_FOUND_WITH_NO_LINK, targetClazz, this.getClass().getSimpleName());
        }
    }

    protected String getDefaultImplementationUrl(){
        return "";
    }
}
