package com.dearnote.domain;

import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String originFileName;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String storeFileName;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String storeFileUrl;

    private Integer size;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_id", nullable = false)
    private Letter letter;
}
