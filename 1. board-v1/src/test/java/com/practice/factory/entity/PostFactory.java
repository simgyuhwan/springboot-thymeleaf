package com.practice.factory.entity;

import com.practice.board.entity.Posts;

public class PostFactory {
    public static Posts createPosts(){
        return new Posts("test1", "content", "writer");
    }

}
