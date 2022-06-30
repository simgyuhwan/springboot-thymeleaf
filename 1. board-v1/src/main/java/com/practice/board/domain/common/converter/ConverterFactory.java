package com.practice.board.domain.common.converter;

import org.springframework.core.convert.converter.Converter;

public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
