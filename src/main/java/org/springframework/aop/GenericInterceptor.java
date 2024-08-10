package org.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GenericInterceptor implements MethodInterceptor {

    private BeforeAdvice beforeAdvice;
    private AfterAdvice afterAdvice;
    private AfterReturningAdvice afterReturningAdvice;
    private ThrowsAdvice throwsAdvice;


    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }

    public void setAfterReturningAdvice(AfterReturningAdvice afterReturningAdvice) {
        this.afterReturningAdvice = afterReturningAdvice;
    }

    public void setThrowsAdvice(ThrowsAdvice throwsAdvice) {
        this.throwsAdvice = throwsAdvice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        try {
            // 前置通知
            if (beforeAdvice != null) {
                beforeAdvice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
            }
            result = invocation.proceed();
        } catch (Exception throwable) {
            //异常通知
            if (throwsAdvice != null) {
                throwsAdvice.throwHandle(throwable,invocation.getMethod(),invocation.getArguments(),invocation.getThis());
            }
        } finally {
            if (afterAdvice != null) {
                afterAdvice.after(invocation.getMethod(),invocation.getArguments(),invocation.getThis());
            }
        }

        // 返回通知
        if (afterReturningAdvice != null) {
            afterReturningAdvice.afterReturning(result, invocation.getMethod(),invocation.getArguments(),invocation.getThis());
        }

        return result;
    }
}
