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
package io.github.rhkiswani.javaff.security.escape;

import io.github.rhkiswani.javaff.factory.AbstractFactory;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.factory.AbstractFactory
 */
public class EscapeersFactory extends AbstractFactory<EscapeHandler> {

    private static EscapeersFactory instance = new EscapeersFactory();

    private EscapeersFactory(){

    }

    public static EscapeersFactory instance(){
        return instance;
    }

    @Override
    public EscapeHandler getDefault(Class targetClazz) {
        return new StringEscapeHandler();
    }

    public static EscapeHandler getEscapeer(Class clazz) {
        return instance.create(clazz);
    }
}
