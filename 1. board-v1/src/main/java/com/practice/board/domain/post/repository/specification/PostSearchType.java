package com.practice.board.domain.post.repository.specification;

import com.practice.board.domain.post.entity.Posts;
import org.springframework.data.jpa.domain.Specification;

public enum PostSearchType {
    TITLE{
        @Override
        public Specification<Posts> equalSearchBy(String title) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
    }, CONTENT{
        @Override
        public Specification<Posts> equalSearchBy(String content) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("content"), "%" + content+ "%");
        }
    }, WRITER{
        @Override
        public Specification<Posts> equalSearchBy(String writer) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("writer"), "%" + writer + "%");
        }
    };

    public abstract Specification<Posts> equalSearchBy(String title);
}
