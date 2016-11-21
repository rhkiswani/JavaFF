package io.github.rhkiswani.jutils.security.encode;

import io.github.rhkiswani.jutils.lang.ArraysHelper;
import io.github.rhkiswani.jutils.security.encode.exception.EncodeException;

public abstract class DefaultEncodeHandler<IN, OUT> implements EncodeHandler<IN, OUT> {

    protected abstract OUT encodeVal(IN in, Object... params);

    @Override
    public OUT encode(IN in, Object... params) throws EncodeException {
        if (in == null){
            return null;
        }
        if (params.length > 0){
            try {
                ArraysHelper.replace(params, null, "");
                return encodeVal(in, params);
            } catch (Throwable t ){
                throw new EncodeException(t);
            }

        }
        return encodeVal(in);
    }

}
