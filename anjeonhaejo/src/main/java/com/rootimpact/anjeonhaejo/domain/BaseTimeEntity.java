package com.rootimpact.anjeonhaejo.domain;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    @PrePersist
    public void prePersist() {
        this.createTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }
}
