package com.leun.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 토큰 관련 테스트용 컨트롤러
 */
@RestController
@RequestMapping(TokenTestController.BASE_URL)
public class TokenTestController {

    public static final String BASE_URL = "/v1/token";
    public static final String TEST_ENDPOINT = "/test";

    /**
     * 토큰 테스트 요청 핸들러
     * @return 성공 메시지
     */
    @GetMapping(TEST_ENDPOINT)
    public String getTokenTest() {
        return "success";
    }
}
