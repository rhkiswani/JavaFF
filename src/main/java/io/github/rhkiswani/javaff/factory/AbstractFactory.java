package io.github.rhkiswani.javaff.factory;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.lang.exceptions.IllegalParamException;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractFactory<IMP_TYPE> {

    public abstract IMP_TYPE getDefault(Class targetClazz) ;

    private Map<Class, IMP_TYPE> map = new LinkedHashMap<>();

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

    public IMP_TYPE getHandlerFor(Class targetClass){
        if (targetClass == null){
            throw new IllegalParamException(SmartException.NULL_VAL, "Target Class");
        }
        for (Class aClass : map.keySet()) {
            if (aClass.isAssignableFrom(targetClass) || targetClass.isAnnotationPresent(aClass)){
                IMP_TYPE t = map.get(aClass);
                return t;
            }
        }
        return getDefault(targetClass);
    }

    public IMP_TYPE getDefault(){
        return getDefault(Object.class);
    }
    public Collection<IMP_TYPE> listHandlers(){
        return map.values();
    }
}
