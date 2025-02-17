package com.atiguigu.topnews.common;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOTLOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed"),
    UNKNOWN_ERROR(506,"unknownError");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
