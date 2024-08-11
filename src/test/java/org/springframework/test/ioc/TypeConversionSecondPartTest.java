package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;

import java.time.LocalDate;

public class TypeConversionSecondPartTest {

    @Test
    public void testConversionService() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

        Car car = applicationContext.getBean("car", Car.class);
        Assertions.assertEquals(car.getPrice(),1000000);
        Assertions.assertEquals(car.getProduceDate(), LocalDate.of(2021, 1, 1));
    }
    
}
