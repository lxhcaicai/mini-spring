package org.springframework.test.aop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.GenericInterceptor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.test.common.WorldServiceAfterAdvice;
import org.springframework.test.common.WorldServiceAfterReturningAdvice;
import org.springframework.test.common.WorldServiceBeforeAdvice;
import org.springframework.test.common.WorldServiceThrowsAdvice;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;
import org.springframework.test.service.WorldServiceWithExceptionImpl;

public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @BeforeEach
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.explode(..))").getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }

    @Test
    public void testJdkDynamicProxy() {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testCglibDynamicProxy() {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testProxyFactory() {
        // 使用JDK动态代理
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();

        // 使用CGLIB动态代理
        advisedSupport.setProxyTargetClass(true);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testBeforeAdvice() {
        // 设置BeforeAdvice
        WorldServiceBeforeAdvice beforeAdvice = new WorldServiceBeforeAdvice();
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setBeforeAdvice(beforeAdvice);

        advisedSupport.setMethodInterceptor(methodInterceptor);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAfterAdvice() {
        //设置AfterAdvice
        WorldServiceAfterAdvice afterAdvice = new WorldServiceAfterAdvice();
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setAfterAdvice(afterAdvice);

        advisedSupport.setMethodInterceptor(methodInterceptor);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAfterReturningAdvice() {
        // 设置afterReturningAdvice
        WorldServiceAfterReturningAdvice afterReturningAdvice = new WorldServiceAfterReturningAdvice();
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setAfterReturningAdvice(afterReturningAdvice);

        advisedSupport.setMethodInterceptor(methodInterceptor);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testThrowsAdvice() {
        WorldService worldService = new WorldServiceWithExceptionImpl();
        // 设置ThrowsAdvice
        WorldServiceAfterReturningAdvice afterReturningAdvice = new WorldServiceAfterReturningAdvice();
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setAfterReturningAdvice(afterReturningAdvice);

        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setTargetSource(new TargetSource(worldService));

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAllAdvice() {
        //设置before、after、afterReturning
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setBeforeAdvice(new WorldServiceBeforeAdvice());
        methodInterceptor.setAfterAdvice(new WorldServiceAfterAdvice());
        methodInterceptor.setAfterReturningAdvice(new WorldServiceAfterReturningAdvice());
        advisedSupport.setMethodInterceptor(methodInterceptor);

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

    @Test
    public void testAllAdviceWithException() {
        WorldService worldService = new WorldServiceWithExceptionImpl();
        //设置before、after、throws
        GenericInterceptor methodInterceptor = new GenericInterceptor();
        methodInterceptor.setBeforeAdvice(new WorldServiceBeforeAdvice());
        methodInterceptor.setAfterAdvice(new WorldServiceAfterAdvice());
        methodInterceptor.setThrowsAdvice(new WorldServiceThrowsAdvice());
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setTargetSource(new TargetSource(worldService));

        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.explode();
    }

}
