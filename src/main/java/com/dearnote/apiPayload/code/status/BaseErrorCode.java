package com.dearnote.apiPayload.code.status;

import com.dearnote.apiPayload.code.ErrorReasonDTO;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
