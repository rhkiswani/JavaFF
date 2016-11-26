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

import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;
import io.github.rhkiswani.javaff.locale.LocaleUtil;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 *
 */
public class SmartException extends RuntimeException{


    public static final String NULL_VAL = "NULL_VAL";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String TYPE_ERROR = "TYPE_ERROR";
    public static final String EXCEEDS_LIMIT = "EXCEEDS_LIMIT";
    public static final String FORMAT_EXCEPTION = "FORMAT_EXCEPTION";
    public static final String ALREADY_EXIST = "ALREADY_EXIST";
    public static final String NEGATIVE_VAL = "NEGATIVE_VAL";
    public static final String HTTP_ERROR = "HTTP_ERROR";
    public static final String NO_IMPLEMENTATION_FOUND = "NO_IMPLEMENTATION_FOUND";

    private Object[] errorMsgParams = null;

    public SmartException(String message) {
        super(message);
    }

    public SmartException(String message, Object... errorMsgParams) {
        super(message);
        this.errorMsgParams = errorMsgParams;
    }

    public SmartException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmartException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (!ArraysUtils.isEmpty(errorMsgParams)){
            return LocaleUtil.getString(super.getMessage(), errorMsgParams);
        } else {
            return LocaleUtil.getString(super.getMessage());
        }
    }
}
