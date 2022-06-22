package com.practice.board.dto;

import com.practice.board.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @Size(min = 1, max = 20)
    @NotBlank(message = "값을 입력해주세요")
    private String title;

    @NotBlank(message = "값을 입력해주세요")
    private String content;

    @NotBlank(message = "값을 입력해주세요")
    @Size(min = 1, max = 20)
    private String writer;

    public PostDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
