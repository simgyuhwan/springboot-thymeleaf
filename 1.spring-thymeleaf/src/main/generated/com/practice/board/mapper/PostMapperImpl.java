package com.practice.board.mapper;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T21:25:49+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_332 (Azul Systems, Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Posts toEntity(PostDto dto) {
        if ( dto == null ) {
            return null;
        }

        String title = null;
        String content = null;
        String writer = null;

        Posts posts = new Posts( title, content, writer );

        return posts;
    }

    @Override
    public PostDto toDto(Posts entity) {
        if ( entity == null ) {
            return null;
        }

        String title = null;
        String content = null;
        String writer = null;

        PostDto postDto = new PostDto( title, content, writer );

        return postDto;
    }
}
