package io.github.rhkiswani.jutils.log;

public interface Log {

    void debug(String message);

    void info(String message);

    void warn(String message);

    void error(String message);

    void error(String message, Exception e);

}
