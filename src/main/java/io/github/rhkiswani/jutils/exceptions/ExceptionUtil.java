package io.github.rhkiswani.jutils.exceptions;

public class ExceptionUtil {

    private ExceptionUtil(){
    }

    public static void handle(Throwable t){
        ExceptionHandlersManager.instance().handle(t);
    }

    public static Throwable getRootCause(Throwable throwable) {
        if (throwable == null) {
            return null;
        }

        Throwable cause = throwable;
        Throwable rootCause;
        while ((rootCause = cause.getCause()) != null) {
            cause = rootCause;
        }

        return cause;
    }
}
