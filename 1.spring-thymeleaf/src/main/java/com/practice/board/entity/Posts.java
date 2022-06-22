package com.practice.board.entity;

import com.practice.board.dto.PostDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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

}
