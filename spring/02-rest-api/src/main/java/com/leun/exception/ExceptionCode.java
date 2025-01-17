package com.leun.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    INVALID_INPUT(400, "Invalid Input Provided"),
    USER_NOT_FOUND(404, "Member Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final Integer exceptionStatus;
    private final String exceptionTitle;
}
