package com.dearnote.service.keyword;

import com.dearnote.domain.Keyword;

public interface KeywordQueryService {

    Keyword getKeyword(Long keywordId);

    Keyword getRandomKeyword();
}
