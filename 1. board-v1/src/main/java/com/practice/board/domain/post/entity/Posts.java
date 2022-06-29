package com.practice.board.domain.post.entity;

import com.practice.board.domain.common.entity.BaseEntity;
import com.practice.board.domain.post.dto.PostDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends BaseEntity {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String writer;

    @Builder
    public Posts(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Posts of(PostDto postDto){
        return Posts
                .builder()
                .content(postDto.getContent())
                .title(postDto.getTitle())
                .writer(postDto.getWriter())
                .build();
    }

    public Posts update(PostDto postDto) {
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        this.writer = postDto.getWriter();
        return this;
    }
}
