package com.dsm.me.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class CustomAsyncExceptionHandler extends BasicExceptionHandler implements AsyncUncaughtExceptionHandler{
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Method name: "+method.getName()+", Param count: "+params.length+", Exception cause: "+ex.getMessage());
    }
}
