package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.service.HelloService;

public class AwareInterfaceTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService",HelloService.class);
        Assertions.assertNotNull(helloService.getApplicationContext());
        Assertions.assertNotNull(helloService.getBeanFactory());
    }
}
