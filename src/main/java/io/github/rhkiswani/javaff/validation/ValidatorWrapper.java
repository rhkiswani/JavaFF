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
package io.github.rhkiswani.javaff.validation;

import io.github.rhkiswani.javaff.validation.exceptions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Mohamed Kiswani
 * @since 0.0.23
 * @see Validator
 */
public class ValidatorWrapper<T> implements Validator<T> {
    private Validator validator;

    public ValidatorWrapper(Validator validator){
        this.validator = validator;
    }

    @Override
    public void validate(T t) throws ValidationException {
        this.validator.validate(t);
    }

    @Override
    public void validateList(List<T> t) throws ValidationException {
        this.validator.validateList(t);
    }

    @Override
    public void validateField(Field field, T t) throws ValidationException {
        this.validator.validateField(field, t);
    }

    @Override
    public void validateMethod(Method method, T t) throws ValidationException {
        this.validator.validateMethod(method, t);
    }
}
