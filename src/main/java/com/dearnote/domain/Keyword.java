package com.dearnote.domain;

import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Keyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String keyword;

    @Column(nullable = true, columnDefinition = "VARCHAR(300)")
    private String sentence;

    @OneToOne(mappedBy = "keyword", fetch = FetchType.LAZY)
    private Letter letter;
}
