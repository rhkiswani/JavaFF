package io.github.rhkiswani.jutils.format;

import java.text.MessageFormat;

class NumberFormatter extends DefaultFormatter<Number, String> {

    @Override
    protected String formatVal(Number number, Object... params) {
        return MessageFormat.format("{0}", number);
    }

}
