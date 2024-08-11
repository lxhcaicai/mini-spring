package org.springframework.test.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.StringToNumberConverterFactory;
import org.springframework.test.common.StringToBooleanConverter;
import org.springframework.test.common.StringToIntegerConverter;

public class TypeConversionFirstPartTest {

    @Test
    public void testStringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("8888");
        Assertions.assertEquals(num,8888);
    }

    @Test
    public void testStringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        Integer intNum = stringToIntegerConverter.convert("8888");
        Assertions.assertEquals(intNum,8888);

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        Long longNum = stringToLongConverter.convert("8888");
        Assertions.assertEquals(longNum,8888L);
    }

    @Test
    public void testGenericConverter() {
        StringToBooleanConverter converter = new StringToBooleanConverter();

        Boolean flag = (Boolean) converter.convert("true", String.class, Boolean.class);
        Assertions.assertTrue(flag);
    }

    @Test
    public void testGenericConversionService() {
        GenericConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(new StringToIntegerConverter());

        Integer intNum = conversionService.convert("8888", Integer.class);
        Assertions.assertTrue(conversionService.canConvert(String.class,Integer.class));
        Assertions.assertEquals(intNum,8888);

        conversionService.addConverterFactory(new StringToNumberConverterFactory());
        Assertions.assertTrue(conversionService.canConvert(String.class,Long.class));
        Long longNum = conversionService.convert("8888",Long.class);
        Assertions.assertEquals(longNum,8888L);

        conversionService.addConverter(new StringToBooleanConverter());
        Assertions.assertTrue(conversionService.canConvert(String.class,Boolean.class));
        Boolean flag = conversionService.convert("true", Boolean.class);
        Assertions.assertTrue(flag);
    }
}
