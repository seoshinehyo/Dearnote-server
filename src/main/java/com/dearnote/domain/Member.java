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
    private MemberStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "letter_box_id", nullable = false)
    private LetterBox letterBox;

    @OneToMany(mappedBy = "sender")
    private List<Letter> senderList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Letter> receiverList = new ArrayList<>();
}
