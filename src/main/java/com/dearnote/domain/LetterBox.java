package com.dearnote.domain;

import jakarta.persistence.*;
import lombok.*;
import com.dearnote.domain.common.BaseEntity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterBox extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "letterBox", cascade = CascadeType.ALL)
    private List<Letter> letterList = new ArrayList<>();


    // LetterBox의 member 필드를 설정하는 setMember 메서드
    public void setMember(Member member) {
        this.member = member;
    }
}