package com.practice.board.domain.common.converter;

import com.practice.board.domain.post.repository.specification.SearchType;
import org.springframework.core.convert.converter.Converter;

public class StringToSearchTypeConverter implements Converter<String, SearchType> {
    @Override
    public SearchType convert(String source) {
        return SearchType.valueOf(source.toUpperCase());
    }

}
