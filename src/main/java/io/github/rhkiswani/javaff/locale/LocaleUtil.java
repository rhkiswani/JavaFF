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
package io.github.rhkiswani.javaff.locale;

import io.github.rhkiswani.javaff.exceptions.SmartException;
import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.utils.StringUtils;
import io.github.rhkiswani.javaff.reflection.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class LocaleUtil {

    private static Map<String, String> DEFAULT_MSGS = null;

    private LocaleUtil(){

    }

    static {
        DEFAULT_MSGS = new HashMap<>();
        DEFAULT_MSGS.put(SmartException.EXCEEDS_LIMIT, "{0} MaxSize is {1}");
        DEFAULT_MSGS.put(SmartException.ALREADY_EXIST, "{0} is already exist {1}");
        DEFAULT_MSGS.put(SmartException.NULL_VAL, "{0} can't be null");
        DEFAULT_MSGS.put(SmartException.NOT_FOUND, "{0} not found");
        DEFAULT_MSGS.put(SmartException.TYPE_ERROR, "{0} is not fit to {1}");
        DEFAULT_MSGS.put(SmartException.FORMAT_EXCEPTION, "Can't format {0} {1}");
        DEFAULT_MSGS.put(SmartException.NEGATIVE_VAL, "{0} should be greater or equal 0");
        DEFAULT_MSGS.put(SmartException.HTTP_ERROR, "failed to connect to {0}");
        DEFAULT_MSGS.put(SmartException.NO_IMPLEMENTATION_FOUND, "No implementation found for {0} you need to set implementation through {1}.instance().add or add {2} to your classpath");
    }

    @Deprecated
    /**
     * use public static String getString(String key, Class targetClass, Object[] params) instead
     */
    public static String getString(String key, Object[] params){
        Class targetClass = ReflectionUtil.getCallerClass(1);
        return getString(key, targetClass, params);
    }

    public static String getString(String key, Class targetClass, Object[] params) {
        LocaleWorker worker = LocaleWorkersFactory.getLocalWorker(targetClass);
        String label = worker.getString(key, params);
        if (!StringUtils.isEmpty(label) && label.equals(key) && DEFAULT_MSGS.get(key) != null){
            return FormatUtil.formatString(DEFAULT_MSGS.get(key), params);
        }
        return label;
    }

}
