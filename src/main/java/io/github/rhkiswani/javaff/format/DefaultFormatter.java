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

import io.github.rhkiswani.javaff.format.exception.FormatException;
import io.github.rhkiswani.javaff.lang.utils.ArraysUtils;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.format.Formatter
 */
public abstract class DefaultFormatter<I, O> extends Formatter<I, O> {

    protected abstract O formatVal(I i, Object[] params);

    @Override
    protected O format(I i, Object[] params) throws FormatException {
        if (i == null){
            return null;
        }
        if (!ArraysUtils.isEmpty(params)){
            try {
                ArraysUtils.replace(params, null, "");
                return formatVal(i, params);
            } catch (FormatException e){
                throw e;
            } catch (Throwable t ){
                throw new FormatException(t.getMessage());
            }

        }
        return formatVal(i, new Object[]{});
    }

}
