package com.practice.board.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @CreatedDate
    @Column(nullable = false,updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime updateDate;

}
