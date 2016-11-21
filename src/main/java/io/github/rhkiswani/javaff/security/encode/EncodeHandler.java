package io.github.rhkiswani.javaff.security.encode;

import io.github.rhkiswani.javaff.security.encode.exception.EncodeException;

public interface EncodeHandler<IN, OUT> {

    OUT encode(IN in, Object... params) throws EncodeException;

}
