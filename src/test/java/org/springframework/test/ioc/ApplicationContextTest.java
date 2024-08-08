package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);

        Assertions.assertEquals(person.getName(),"ivy");

        //name属性在CustomBeanFactoryPostProcessor中被修改为ivy
        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);

        //brand属性在CustomerBeanPostProcessor中被修改为lamborghini
        Assertions.assertEquals(car.getBrand(),"lamborghini");
    }
}
