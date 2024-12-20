package com.dearnote.web.dto.OAuth2;


import java.util.Map;

public class NaverResponse implements OAuth2response {



    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {
        // attribute에서 "response" 값을 가져오고, 그 값이 Map인지 확인합니다.
        this.attribute = (Map<String, Object>) attribute.get("response");
    }


    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
