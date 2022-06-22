package com.practice.board.mapper;

import com.practice.board.entity.Posts;

public interface EntityMapper<D, E> {
    E toEntity(final D dto);
    D toDto(final E entity);
}
