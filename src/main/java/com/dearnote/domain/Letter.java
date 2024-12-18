package com.dearnote.domain;

import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;
import com.dearnote.domain.enums.LetterStatus;
import com.dearnote.domain.enums.LetterType;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Letter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'RECEIVED'")
    private LetterType type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    private LetterStatus status;

    @Column(nullable = true, columnDefinition = "VARCHAR(100)")
    private String imageDescription;

    // 글씨 폰트

    // 편지지

    // 실링 왁스

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letterBox_id", nullable = false)
    private LetterBox letterBox;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
