package com.dearnote.service.keyword;

import com.dearnote.apiPayload.code.status.ErrorStatus;
import com.dearnote.apiPayload.exception.handler.MemberHandler;
import com.dearnote.domain.Keyword;
import com.dearnote.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordQueryServiceImpl implements KeywordQueryService {

    private final KeywordRepository keywordRepository;

    @Override
    public Keyword getKeyword(Long keywordId) {
        return keywordRepository.findById(keywordId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.KEYWORD_NOT_FOUND));
    }
}
