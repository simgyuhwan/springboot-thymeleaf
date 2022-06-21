package com.practice.board.dto;

import com.practice.board.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    //@Max(value = 20 , message = "최대 20자까지 가능합니다.")
    @NotBlank(message = "값을 입력해주세요")
    private String title;

    @NotBlank(message = "값을 입력해주세요")
    private String content;

    @NotBlank(message = "값을 입력해주세요")
    //@Max(value = 10, message = "최대 10자까지 가능합니다.")
    private String writer;

}
