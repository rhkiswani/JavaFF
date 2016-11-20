package io.github.rhkiswani.jutils.locale;

import io.github.rhkiswani.jutils.beans.ValuesHolder;
import io.github.rhkiswani.jutils.format.FormatUtil;
import io.github.rhkiswani.jutils.lang.annotations.EqualsField;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
