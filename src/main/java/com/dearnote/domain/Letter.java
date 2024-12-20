package com.dearnote.domain;

import com.dearnote.domain.enums.*;
import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;

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
    private LetterStatus status;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean mark;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;

    private Boolean isPublic;

    @Column(nullable = true, columnDefinition = "VARCHAR(100)")
    private String imageDescription;

    @Enumerated(EnumType.STRING)
    private Font font;

    @Enumerated(EnumType.STRING)
    private LetterPaper letterPaper;

    @Enumerated(EnumType.STRING)
    private Wax wax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_box_id", nullable = false)
    private LetterBox letterBox;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;
}
