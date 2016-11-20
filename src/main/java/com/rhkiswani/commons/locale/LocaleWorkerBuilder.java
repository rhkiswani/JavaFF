package com.rhkiswani.commons.locale;

import java.util.Locale;

public class LocaleWorkerBuilder {
    private LocaleWorker worker = new DefaultLocaleWorker();

    public LocaleWorkerBuilder name(String name){
        worker.setName(name);
        return this;
    }

    public LocaleWorkerBuilder path(String path){
        worker.setPath(path);
        return this;
    }

    public LocaleWorkerBuilder locale(Locale locale){
        worker.setLocale(locale);
        return this;
    }

    public LocaleWorker build(){
        worker.reload();
        return worker;
    }
}
