package com.dsm.me.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BAD_REQUEST(400, "Validated fail"),
    USER_NOT_FOUND(404, "User not found"),
    EMAIL_OVERLAP(409,"Email Overlap"),
    SAVE_CODE_NOT_FOUND(404, "Save code not found"),
    EMAIL_NOT_MATCH_ID(404, "Email not matched Id"),
    EMAIL_DOSE_NOT_SEND(400, "Email doesn't send"),
    EMAIL_NOT_MATCH_PASSWORD(404, "Email not matched password");

    private final int code;
    private final String message;
}
