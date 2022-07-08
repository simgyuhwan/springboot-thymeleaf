package com.practice.board.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgFileDto {
    private String imgName;
    private String oriImgName;
    private String imgUrl;
}
