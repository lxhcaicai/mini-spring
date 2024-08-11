package org.springframework.core.convert.converter;

/**
 * 类型转换抽象接口
 */
public interface Converter<S,T> {
    T convert(S source);
}
