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

import io.github.rhkiswani.javaff.log.Log;
import io.github.rhkiswani.javaff.log.LogFactory;

import java.text.MessageFormat;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.format.DefaultFormatter
 * @see io.github.rhkiswani.javaff.format.Formatter 
 */
class StringFormatter extends DefaultFormatter<String, String> {
    private static final Log LOGGER = LogFactory.getLogger(StringFormatter.class);

    @Override
    protected String formatVal(String string, Object... params) {
        try {
            return MessageFormat.format(string, params);
        } catch (Exception e){
            LOGGER.error("Failed to format {0}", string);
            return string;
        }
    }
}
