package com.practice.board.config;

import com.practice.board.domain.common.converter.StringToEnumConverterFactory;
import com.practice.board.domain.common.converter.StringToSearchTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

}
