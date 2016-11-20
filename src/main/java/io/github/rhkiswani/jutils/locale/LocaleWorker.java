package io.github.rhkiswani.jutils.locale;

import java.util.Locale;

public interface LocaleWorker {

    void setName(String name);

    void setFilesExtensions(String extensions);

    void setLocale(Locale locale);

    void reload();

    void setPath(String path);

    String getString(String key, Object... params);

}
