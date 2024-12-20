package com.dearnote.apipayload.code;

import com.dearnote.apiPayload.code.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}