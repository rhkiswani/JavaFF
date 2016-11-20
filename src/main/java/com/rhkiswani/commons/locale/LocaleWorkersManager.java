package com.rhkiswani.commons.locale;

import com.rhkiswani.commons.exceptions.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class LocaleWorkersManager {
    private static LocaleWorkersManager registry = new LocaleWorkersManager();
    private List<LocaleWorker> localeWorkers = new ArrayList<LocaleWorker>();

    private LocaleWorkersManager(){
        add(new LocaleWorkerBuilder().path("exceptions/").build());
        add(new LocaleWorkerBuilder().path("exceptions/mysql/").build());
        add(new LocaleWorkerBuilder().path("exceptions/oracle/").build());
        add(new LocaleWorkerBuilder().path("app/").build());
    }

    public static LocaleWorkersManager instance(){
        return registry;
    }

    public void add(LocaleWorker localeWorker){
        localeWorkers.add(localeWorker);
    }

    public Boolean remove(ExceptionHandler exceptionHandler){
        return localeWorkers.remove(exceptionHandler);
    }

    public List<LocaleWorker> listWorkers(){
        return localeWorkers;
    }

    public String getString(String key, Object... params) {
        List<LocaleWorker> localeWorkers = listWorkers();
        for (int i = localeWorkers.size() - 1 ; i >= 0  ; i--){
            LocaleWorker worker = localeWorkers.get(i);
            String val = worker.getString(key, params);
            if (val != null && !val.equals(key)){
                return val;
            }
        }
        return key;
    }
}
