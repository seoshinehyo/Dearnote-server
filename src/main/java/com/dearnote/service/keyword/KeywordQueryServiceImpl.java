package com.dearnote.service.keyword;

import com.dearnote.apipayload.code.status.ErrorStatus;
import com.dearnote.apipayload.exception.handler.KeywordHandler;
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
                .orElseThrow(() -> new KeywordHandler(ErrorStatus.KEYWORD_NOT_FOUND));
    }

    @Override
    public Keyword getRandomKeyword() {
        Keyword keyword = keywordRepository.findRandomKeyword();
        if (keyword == null) {
            throw new KeywordHandler(ErrorStatus.KEYWORD_NOT_FOUND);
        }
        return keyword;
    }
}
