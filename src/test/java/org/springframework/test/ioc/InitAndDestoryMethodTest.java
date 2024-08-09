package org.springframework.test.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitAndDestoryMethodTest {

    @Test
    public void testInitAndDestoryMethod() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook(); //或者手动关闭 applicationContext.close();
    }
}
