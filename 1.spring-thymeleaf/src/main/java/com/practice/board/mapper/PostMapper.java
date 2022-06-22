package com.practice.board.mapper;

import com.practice.board.dto.PostDto;
import com.practice.board.entity.Posts;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostDto, Posts>{
    PostMapper MAPPER = Mappers.getMapper(PostMapper.class);
}
