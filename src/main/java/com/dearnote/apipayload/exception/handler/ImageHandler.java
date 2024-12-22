package com.dearnote.apipayload.exception.handler;

import com.dearnote.apipayload.code.BaseErrorCode;
import com.dearnote.apipayload.exception.GeneralException;

public class ImageHandler extends GeneralException {

    public ImageHandler(BaseErrorCode code) {
        super(code);
    }
}
