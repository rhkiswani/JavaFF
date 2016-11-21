package io.github.rhkiswani.javaff.format;

import io.github.rhkiswani.javaff.format.exception.FormatException;

public interface Formatter<IN, OUT> {

    OUT format(IN in, Object... params) throws FormatException;

}
