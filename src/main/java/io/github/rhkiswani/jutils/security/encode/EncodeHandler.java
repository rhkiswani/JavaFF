package io.github.rhkiswani.jutils.security.encode;

import io.github.rhkiswani.jutils.security.encode.exception.EncodeException;

public interface EncodeHandler<IN, OUT> {

    OUT encode(IN in, Object... params) throws EncodeException;

}
