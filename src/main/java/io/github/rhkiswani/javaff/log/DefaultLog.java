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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mohamed Kiswani
 * @since 0.0.1
 * @see io.github.rhkiswani.javaff.log.Log
 */
class DefaultLog implements Log {
    private Logger logger = null ;

    public DefaultLog(Class clazz){
        this.logger = Logger.getLogger(clazz.getName());
    }

    public void debug(String message, Object... params) {
        logger.log(Level.FINE, message);
    }

    public void info(String message, Object... params) {
        logger.log(Level.INFO, message);
    }

    public void warn(String message, Object... params) {
        logger.log(Level.WARNING, message);
    }

    public void error(String message, Object... params) {
        logger.log(Level.SEVERE, message);
    }

    public void error(String message, Exception e, Object... params) {
        logger.log(Level.SEVERE, message, e);
    }

}
