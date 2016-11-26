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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public abstract class AbstractFactory<IMP_TYPE> {

    private Map<Class, IMP_TYPE> map = new LinkedHashMap<>();

    private IMP_TYPE userDefaultImpl = null;

    public void add(Class targetClass, IMP_TYPE t){
        if (map.get(targetClass) != null){
            throw new IllegalParamException(SmartException.ALREADY_EXIST, targetClass, "please use overrideImp method instated");
        }
        map.put(targetClass, t);
    }

    public void overrideImp(Class targetClass, IMP_TYPE t){
        map.put(targetClass, t);
    }

    public IMP_TYPE remove(Class targetClass){
        return map.remove(targetClass);
    }

    protected IMP_TYPE create(Class targetClass){
        if (targetClass == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "Target Class");
        }
        if (userDefaultImpl != null){
            return userDefaultImpl;
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

    public Collection<IMP_TYPE> listImplementations(){
        return map.values();
    }

    public void setDefault(IMP_TYPE userDefaultImpl) {
        this.userDefaultImpl = userDefaultImpl;
    }

    protected IMP_TYPE getDefault(Class targetClazz) {
        throw new NoImplementationFoundException(SmartException.NO_IMPLEMENTATION_FOUND, this.getClass().getSimpleName(), this.getClass().getSimpleName(), getDefaultImplementationUrl());
    }

    protected String getDefaultImplementationUrl(){
        return "https://mvnrepository.com";
    }
}
