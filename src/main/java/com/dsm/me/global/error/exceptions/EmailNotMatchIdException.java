package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class EmailNotMatchIdException extends BasicException {
    public EmailNotMatchIdException(){
        super(ErrorCode.EMAIL_NOT_MATCH_ID);
    }
}
