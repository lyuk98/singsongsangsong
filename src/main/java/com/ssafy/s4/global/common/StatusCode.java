package com.ssafy.s4.global.common;

import lombok.Getter;

@Getter
public enum StatusCode {

    // COMMON
    SUCCESS(true, 200, "요청에 성공하였습니다."),
    FAIL(false, 400, "조회된 값이 없습니다.");

    // Success
    private final boolean isSuccess;
    private final int code;
    private final String message;
    StatusCode(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
