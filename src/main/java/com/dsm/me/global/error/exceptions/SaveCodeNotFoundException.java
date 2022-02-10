package com.dsm.me.global.error.exceptions;

import com.dsm.me.global.error.BasicException;
import com.dsm.me.global.error.ErrorCode;

public class SaveCodeNotFoundException extends BasicException {
    public SaveCodeNotFoundException(){
        super(ErrorCode.SAVE_CODE_NOT_FOUND);
    }
}
