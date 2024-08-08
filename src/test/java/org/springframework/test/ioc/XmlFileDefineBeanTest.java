package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        Assertions.assertEquals(person.getName(),"derek");
        Assertions.assertEquals(person.getCar().getBrand(),"porsche");

        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
        Assertions.assertEquals(car.getBrand(),"porsche");
    }
}
