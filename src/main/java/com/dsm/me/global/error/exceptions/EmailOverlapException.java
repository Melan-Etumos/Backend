package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class EmailOverlapException extends BasicException {
    public EmailOverlapException() {
        super(ErrorCode.EMAIL_OVERLAP);
    }
}
