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
package io.github.rhkiswani.javaff.beans;

import io.github.rhkiswani.javaff.json.JsonHandler;
import io.github.rhkiswani.javaff.json.JsonHandlerFactory;
import io.github.rhkiswani.javaff.lang.utils.ObjectUtils;
import io.github.rhkiswani.javaff.lang.utils.StringUtils;

import java.io.Serializable;
/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class ValuesHolder<T> implements Serializable{

    @Override
    public boolean equals(Object obj) {
        return ObjectUtils.isEqual(this, obj);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.toHashCode(this);
    }

    @Override
    public String toString() {
        return StringUtils.toString(this);
    }

    public T clone() {
        JsonHandler jh = JsonHandlerFactory.getJsonHandler(this.getClass());
        String json = jh.toJson(this);
        return (T) jh.fromJson(json, getClass());
    }
}
