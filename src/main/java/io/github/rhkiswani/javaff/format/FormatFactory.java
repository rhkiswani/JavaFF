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
package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

import java.util.Date;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.factory.AbstractFactory
 *
 */
public class FormatFactory extends AbstractFactory<Formatter> {

    private static FormatFactory instance = new FormatFactory();

    private FormatFactory(){
        add(Date.class, new DateFormatter());
        add(String.class, new StringFormatter());
        add(Number.class, new NumberFormatter());
    }

    public static FormatFactory instance(){
        return instance;
    }

    protected Formatter getDefault(Class targetClazz){
        return new StringFormatter();
    }

    public static Formatter getFormatter(Class aClass) {
        return instance.create(aClass);
    }
}
