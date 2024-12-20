package com.dearnote;

import com.dearnote.domain.LetterBox;
import com.dearnote.domain.Member;
import com.dearnote.domain.enums.MemberStatus;
import com.dearnote.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LoginTest {

    @Autowired
    private MemberRepository  memberRepository;

    @Test
    public void testMemberCreation() {
        Member member = Member.builder()
                .name("John Doe")
                .username("johndoe")
                .role("ROLE_USER")
                .email("johndoe@example.com")
                .status(MemberStatus.ACTIVE)
                .build();

        memberRepository.save(member);

        // LetterBox가 제대로 생성되었는지 검증
        assertNotNull(member.getLetterBox());
        assertEquals(member, member.getLetterBox().getMember());
    }


}
