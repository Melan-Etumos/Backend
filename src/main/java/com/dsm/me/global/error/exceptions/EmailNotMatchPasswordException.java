package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class EmailNotMatchPasswordException extends BasicException {
    public EmailNotMatchPasswordException(){
        super(ErrorCode.EMAIL_NOT_MATCH_PASSWORD);
    }
}
