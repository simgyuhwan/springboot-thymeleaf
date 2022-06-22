package com.practice.board.mapper;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.entity.Posts.PostsBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T10:28:09+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Posts toEntity(PostDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        PostsBuilder posts = Posts.builder();

        posts.title( arg0.getTitle() );
        posts.content( arg0.getContent() );
        posts.writer( arg0.getWriter() );

        return posts.build();
    }

    @Override
    public PostDto toDto(Posts arg0) {
        if ( arg0 == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setId( arg0.getId() );
        postDto.setTitle( arg0.getTitle() );
        postDto.setContent( arg0.getContent() );
        postDto.setWriter( arg0.getWriter() );

        return postDto;
    }
}
