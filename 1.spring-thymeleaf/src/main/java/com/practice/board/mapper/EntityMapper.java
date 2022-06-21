package com.practice.board.mapper;

import com.practice.board.entity.Posts;

public interface EntityMapper<D, E> {
    Posts toEntity(final D dto);
    D toDto(final E entity);
}
