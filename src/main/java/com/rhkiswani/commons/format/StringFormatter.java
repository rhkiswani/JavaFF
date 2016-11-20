package com.rhkiswani.commons.format;

import java.text.MessageFormat;

class StringFormatter extends DefaultFormatter<String, String>{

    @Override
    protected String formatVal(String string, Object... params) {
        return MessageFormat.format(string, params);
    }
}
