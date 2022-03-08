package com.dsm.me.global.error.exceptions.token;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;


public class TokenException extends RuntimeException {
    private final TokenErrorCode errorCode;
    public TokenException(TokenErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
