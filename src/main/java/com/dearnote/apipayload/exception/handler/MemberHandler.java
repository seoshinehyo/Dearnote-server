package com.dearnote.apiPayload.exception.handler;

import com.dearnote.apiPayload.code.BaseErrorCode;
import com.dearnote.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {

    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
