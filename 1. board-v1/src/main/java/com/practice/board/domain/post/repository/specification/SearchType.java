package com.practice.board.domain.post.repository.specification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.practice.board.domain.post.entity.Posts;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

public enum SearchType {
    TITLE("title"){
        @Override
        public Specification<Posts> equalSearchBy(String title) {
            return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
    },
    CONTENT("content"){
        @Override
        public Specification<Posts> equalSearchBy(String content) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("content"), "%" + content+ "%");
        }
    },
    WRITER("writer"){
        @Override
        public Specification<Posts> equalSearchBy(String writer) {
            return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("writer"), "%" + writer + "%");
        }
    };

    @Getter
    private final String value;

    SearchType(String value){
        this.value = value;
    }

    public abstract Specification<Posts> equalSearchBy(String title);
}
