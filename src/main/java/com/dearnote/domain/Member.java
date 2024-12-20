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

    @Column(nullable = false)
    private String username;


    private String role;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    private LetterBox letterBox;

    @OneToMany(mappedBy = "sender")
    private List<Letter> senderList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Letter> receiverList = new ArrayList<>();

    public void updateEmailAndName(String email, String name) {
        this.email = email;
        this.name = name;
    }

}
