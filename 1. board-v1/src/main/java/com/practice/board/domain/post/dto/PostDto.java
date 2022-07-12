package com.practice.board.domain.post.dto;

import com.practice.board.domain.post.entity.Posts;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

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

    private String writer;

    private LocalDate createdDate;

    private LocalDate updateDate;

    private List<ImgFileDto> imgFileDtoList = new ArrayList<>();

    private List<CommentResponseDto> comments;

    public PostDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public PostDto(String writer){
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

    public PostDto setImgFileDtoList(List<ImgFileDto> imgFileDtoList){
        this.imgFileDtoList = imgFileDtoList;
        return this;
    }

    public PostDto setWriter(String writer){
        this.writer = writer;
        return this;
    }

    public PostDto setComments(List<CommentResponseDto> comments){
        this.comments = comments;
        return this;
    }
}
