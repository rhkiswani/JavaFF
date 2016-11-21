package io.github.rhkiswani.javaff.log;

public interface Log {

    void debug(String message, Object... params);

    void info(String message, Object... params);

    void warn(String message, Object... params);

    void error(String message, Object... params);

    void error(String message, Exception e, Object... params);

}
