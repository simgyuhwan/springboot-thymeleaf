package com.practice.factory.entity;

import com.practice.board.domain.post.entity.Posts;

public class PostFactory {
    public static Posts createPosts(){
        return new Posts("test1", "content", "writer");
    }

}
