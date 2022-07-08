package com.practice.board.domain.post.dto;

import com.practice.board.domain.post.entity.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private List<ImgFileDto> imgFileDtos = new ArrayList<>();

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
                .id(posts.getId())
                .content(posts.getContent())
                .title(posts.getTitle())
                .writer(posts.getWriter())
                .createdDate(posts.getCreatedDate().toLocalDate())
                .updateDate(posts.getUpdateDate().toLocalDate())
                .build();
    }
}
