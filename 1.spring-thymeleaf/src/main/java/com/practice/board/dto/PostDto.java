package com.practice.board.dto;

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

    @Max(value = 20 , message = "최대 20자까지 가능합니다.")
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    @Max(value = 10, message = "최대 10자까지 가능합니다.")
    private String writer;
}
