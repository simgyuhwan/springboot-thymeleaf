package com.practice.board.dto;

import com.practice.board.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

    private LocalDate createdDate;

    private LocalDate updateDate;


    public PostDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Builder
    public PostDto(Long id, String title, String content, String writer, LocalDate createdDate, LocalDate updateDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public static PostDto of(Posts posts){
        return PostDto
                .builder()
                .content(posts.getContent())
                .title(posts.getTitle())
                .writer(posts.getWriter())
                .createdDate(posts.getCreatedDate().toLocalDate())
                .updateDate(posts.getUpdateDate().toLocalDate())
                .build();
    }
}
