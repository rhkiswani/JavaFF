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

package io.github.rhkiswani.javaff.log;

import io.github.rhkiswani.javaff.locale.LocaleUtil;

/**
 * @author Mohamed Kiswani
 * @since 0.0.17
 *
 */
public class LogWrapper implements Log{

    private final Log log;

    public LogWrapper(Log log) {
        this.log = log;
    }

    @Override
    public void debug(String message, Object... params) {
        log.debug(LocaleUtil.getString(message, params));
    }

    @Override
    public void info(String message, Object... params) {
        log.info(LocaleUtil.getString(message, params));
    }

    @Override
    public void warn(String message, Object... params) {
        log.warn(LocaleUtil.getString(message, params));
    }

    @Override
    public void error(String message, Object... params) {
        log.error(LocaleUtil.getString(message, params));
    }

    @Override
    public void error(String message, Exception e, Object... params) {
        log.error(LocaleUtil.getString(message, params), e);
    }
}
