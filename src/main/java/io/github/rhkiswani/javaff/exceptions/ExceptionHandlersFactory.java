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
package io.github.rhkiswani.javaff.exceptions;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

/**
 * @author Mohamed Kiswani
 * @see io.github.rhkiswani.javaff.factory.AbstractFactory
 * @since 0.0.1
 *
 */
public class ExceptionHandlersFactory extends AbstractFactory<ExceptionHandler>{
    private static ExceptionHandlersFactory instance = new ExceptionHandlersFactory();

    private ExceptionHandlersFactory(){
        add(Throwable.class, new DefaultExceptionHandler());
    }

    public static ExceptionHandlersFactory instance(){
        return instance;
    }

    @Override
    protected ExceptionHandler getDefault(Class targetClazz) {
        return new DefaultExceptionHandler();
    }

    public static ExceptionHandler getExceptionHandler(Class aClass) {
        return instance.create(aClass);
    }
}
