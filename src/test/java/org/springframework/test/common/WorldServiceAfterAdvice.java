package org.springframework.test.common;


import org.springframework.aop.AfterAdvice;

import java.lang.reflect.Method;

public class WorldServiceAfterAdvice implements AfterAdvice {

    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice: do something after the earth explodes");
    }
}
