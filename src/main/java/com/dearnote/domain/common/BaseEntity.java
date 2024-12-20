package com.dearnote.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @Column(columnDefinition = "DATETIME(6)")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME(6)")
    @CreationTimestamp
    private LocalDateTime updatedAt;
}
