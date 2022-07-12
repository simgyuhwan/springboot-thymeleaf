package com.practice.board.domain.post.entity;

import com.practice.board.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Entity
@Table(name ="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    @CreatedDate
    @Column(name = "created_date")
    private String createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private String modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
