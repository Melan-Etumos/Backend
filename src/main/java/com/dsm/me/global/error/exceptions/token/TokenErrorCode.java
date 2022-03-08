package com.dsm.me.global.error.exceptions.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode {
    INVALID_SIGNATURE(401, "JWT가 올바르게 구성되지 않음"),
    TOKEN_EXPIRED(401, "유효기간 초과"),
    UNSUPPORTED_TOKEN(401, "형식이 일치하지 않음"),
    SIGNATURE_TOKEN(401, "서명 일치하지 않음"),
    INVALID_TOKEN(401, "잘못된 토큰");

    private final int code;
    private final String message;
}
