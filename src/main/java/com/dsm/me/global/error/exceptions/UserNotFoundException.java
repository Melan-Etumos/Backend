package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
