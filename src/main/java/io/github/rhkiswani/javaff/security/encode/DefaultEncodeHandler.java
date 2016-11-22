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
package io.github.rhkiswani.javaff.security.encode;

import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;
import io.github.rhkiswani.javaff.security.encode.exception.EncodeException;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.security.encode.EncodeHandler
 */
public abstract class DefaultEncodeHandler<IN, OUT> implements EncodeHandler<IN, OUT> {

    protected abstract OUT encodeVal(IN in, Object... params);

    @Override
    public OUT encode(IN in, Object... params) throws EncodeException {
        if (in == null){
            return null;
        }
        if (params.length > 0){
            try {
                ArraysUtils.replace(params, null, "");
                return encodeVal(in, params);
            } catch (Throwable t ){
                throw new EncodeException(t);
            }

        }
        return encodeVal(in);
    }

}
