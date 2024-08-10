package org.springframework.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

public interface ThrowsAdvice extends Advice {
    void throwHandle(Throwable throwable, Method method, Object[] args, Object target);
}
