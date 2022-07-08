package com.practice.board.domain.post.entity;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "imgFile")
public class ImgFile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Posts.class,
    cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Posts posts;

}
