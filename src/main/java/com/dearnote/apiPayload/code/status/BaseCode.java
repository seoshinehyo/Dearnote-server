package com.dearnote.apiPayload.code.status;

import com.dearnote.apiPayload.code.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}
