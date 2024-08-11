package org.springframework.test.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.service.WorldService;

public class AutoProxyTest {

    @Test
    public void testAutoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }

    @Test
    public void testPopulateProxyBeanWithPropertyValues() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:populate-proxy-bean-with-property-values.xml");
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
        Assertions.assertEquals(worldService.getName(), "earth");
    }
}
