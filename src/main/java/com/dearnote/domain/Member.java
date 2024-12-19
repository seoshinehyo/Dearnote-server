package com.dearnote.domain;

import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;
import com.dearnote.domain.enums.MemberStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letterBox_id", nullable = false)
    private LetterBox letterBox;

    @OneToMany(mappedBy = "sender")
    private List<Letter> sentLetters = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Letter> receivedLetters = new ArrayList<>();
}
