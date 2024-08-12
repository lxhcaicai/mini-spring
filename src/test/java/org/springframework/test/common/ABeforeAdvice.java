package org.springframework.test.common;

import org.springframework.aop.BeforeAdvice;

import java.lang.reflect.Method;

public class ABeforeAdvice implements BeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }
}
