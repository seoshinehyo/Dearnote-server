package com.dearnote.service;

import com.dearnote.domain.Letter;
import org.springframework.data.domain.Page;

public interface MemberQueryService {

    Page<Letter> getLetterList(Long memberId, Integer page);
}
