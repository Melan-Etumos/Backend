package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class EmailSendException extends BasicException {
    public EmailSendException(){
        super(ErrorCode.EMAIL_DOSE_NOT_SEND);
    }
}
