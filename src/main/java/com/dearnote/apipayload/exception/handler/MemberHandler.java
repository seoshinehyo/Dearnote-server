package com.dearnote.apipayload.exception.handler;

import com.dearnote.apipayload.code.BaseErrorCode;
import com.dearnote.apipayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
