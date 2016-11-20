package com.rhkiswani.commons.format;

import com.rhkiswani.commons.format.exception.FormatException;

public interface Formatter<IN, OUT> {

    OUT format(IN in, Object... params) throws FormatException;

}
