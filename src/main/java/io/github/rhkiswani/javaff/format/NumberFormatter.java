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

import java.text.MessageFormat;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.format.DefaultFormatter
 * @see io.github.rhkiswani.javaff.format.Formatter
 */
class NumberFormatter extends DefaultFormatter<Number, String> {

    @Override
    protected String formatVal(Number number, Object... params) {
        return MessageFormat.format("{0}", number);
    }

    public String format(Number num){
        return format(num, new Object[]{});
    }
}
