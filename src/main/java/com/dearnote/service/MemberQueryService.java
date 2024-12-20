package com.dearnote.service;

import com.dearnote.domain.Letter;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    // 전체 편지함 조회
    Page<Letter> getAllLetterList(Long memberId, Integer page);

}
