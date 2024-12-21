package com.dearnote.apipayload.exception.handler;

import com.dearnote.apipayload.code.BaseErrorCode;
import com.dearnote.apipayload.exception.GeneralException;

public class LetterHandler extends GeneralException {

    public LetterHandler(BaseErrorCode code) {
        super(code);
    }
}
