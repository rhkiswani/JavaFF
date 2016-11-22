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

import org.slf4j.Logger;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.log.Log
 */
class Slf4jLog implements Log {
    private final Logger logger;

    public Slf4jLog(Class clazz){
        logger = org.slf4j.LoggerFactory.getLogger(clazz);
    }

    public Slf4jLog(Logger logger, Object... params) {
        this.logger = logger;
    }

    public void debug(String message, Object... params) {
        logger.debug(message);
    }

    public void info(String message, Object... params) {
        logger.info(message);
    }

    public void warn(String message, Object... params) {
        logger.warn(message);
    }

    public void error(String message, Object... params) {
        logger.error(message);
    }

    public void error(String message, Exception e, Object... params) {
        logger.error(message, e);
    }
}
