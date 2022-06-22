package com.practice.mapper;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import com.practice.board.mapper.PostMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MapperTest {

    @Test
    public void entityToDtoTest(){
        //given
        Posts posts = Posts.builder()
                .writer("writer")
                .title("title")
                .content("content")
                .build();

        //when
        PostDto postDto = PostMapper.MAPPER.toDto(posts);

        //then
        PostDto postDto2 = new PostDto("title", "content", "writer");
        assertThat(postDto).usingRecursiveComparison().isEqualTo(postDto2);
    }

    @Test
    public void dtoToEntityTest(){
        //given
        PostDto postDto = new PostDto("title", "content", "writer");

        //when
        Posts posts = PostMapper.MAPPER.toEntity(postDto);

        //then
        Posts posts2 = Posts.builder()
                .content("content")
                .writer("writer")
                .title("title")
                .build();
        assertThat(posts).usingRecursiveComparison().isEqualTo(posts2);

    }
}
