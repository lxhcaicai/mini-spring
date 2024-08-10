package org.springframework.test.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.test.service.HelloService;

import java.lang.reflect.Method;

public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* org.springframework.test.service.HelloService.*(..))");
        Class<HelloService> helloServiceClass = HelloService.class;
        Method method = helloServiceClass.getDeclaredMethod("sayHello");

        Assertions.assertTrue(pointcut.matches(helloServiceClass));
        Assertions.assertTrue(pointcut.matches(method,helloServiceClass));
    }
}
