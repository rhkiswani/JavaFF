package io.github.rhkiswani.jutils.format;

import io.github.rhkiswani.jutils.format.exception.FormatException;

public interface Formatter<IN, OUT> {

    OUT format(IN in, Object... params) throws FormatException;

}
