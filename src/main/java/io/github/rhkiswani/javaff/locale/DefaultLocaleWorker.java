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
package io.github.rhkiswani.javaff.locale;

import io.github.rhkiswani.javaff.beans.ValuesHolder;
import io.github.rhkiswani.javaff.format.FormatUtil;
import io.github.rhkiswani.javaff.lang.annotations.EqualsField;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Mohamed Kiswani
 * @email rhkiswani@gmail.com
 * @url https://github.com/rhkiswani
 * @since 0.0.1
 *
 */
class DefaultLocaleWorker extends ValuesHolder<DefaultLocaleWorker> implements LocaleWorker {
    @EqualsField
    private String name = "messages";
    private String extensions = ".properties";
    private String path = "";
    private Locale locale = Locale.US;
    private ResourceBundle bundle = null;

    public DefaultLocaleWorker(){

    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setFilesExtensions(String extensions) {
        this.extensions = extensions;
    }

    public String getExtensions() {
        return extensions;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setPath(String path) {
        if (path == null || path.isEmpty()){
            this.path = "";
            return ;
        }
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        if (!path.endsWith("/") && path.length() > 1){
            path = path + "/";
        }
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getString(String key, Object... params) {
        if (bundle == null){
            reload();
        }
        if (key == null){
            return null;
        }
        String val = "";
        try {
            val = bundle.getString(key);
        }catch (MissingResourceException e){
            val = key;
        }
        return FormatUtil.formatString(val, params);
    }

    @Override
    public void reload() {
        synchronized (DefaultLocaleWorker.class){
            bundle = ResourceBundle.getBundle(getPath() + getName(), locale);
        }
    }

}
