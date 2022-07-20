package com.practice.board.domain.post.entity;

import com.practice.board.domain.common.entity.BaseEntity;
import com.practice.board.domain.post.dto.PostDto;
import com.practice.board.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity @Getter
@Table(name = "posts" )
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends BaseEntity {

    @Id @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob @Column(name= "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id ASC")
    private List<Comment> comments;

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
