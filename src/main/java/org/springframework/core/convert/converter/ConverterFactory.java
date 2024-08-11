package org.springframework.core.convert.converter;

/**
 * 类型转换工厂
 * @param <S>
 * @param <R>
 */
public interface ConverterFactory<S,R> {

    <T extends  R> Converter<S,T> getConverter(Class<T> targetType);
}
