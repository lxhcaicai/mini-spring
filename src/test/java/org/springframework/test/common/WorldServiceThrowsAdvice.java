package org.springframework.test.common;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class WorldServiceThrowsAdvice implements ThrowsAdvice {
    @Override
    public void throwHandle(Throwable throwable, Method method, Object[] args, Object target) {
        System.out.println("ThrowsAdvice: do something when the earth explodes function throw an exception");
    }
}
