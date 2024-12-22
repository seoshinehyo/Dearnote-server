package com.dearnote.apipayload.exception.handler;

import com.dearnote.apipayload.code.BaseErrorCode;
import com.dearnote.apipayload.exception.GeneralException;

public class KeywordHandler extends GeneralException {

    public KeywordHandler(BaseErrorCode code) {
        super(code);
    }
}
