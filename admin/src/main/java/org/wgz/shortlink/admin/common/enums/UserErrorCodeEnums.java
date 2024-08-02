package org.wgz.shortlink.admin.common.enums;

import org.wgz.shortlink.admin.common.convention.errorCode.IErrorCode;

public enum UserErrorCodeEnums implements IErrorCode {

    USER_TOKEN_FAIL("A000200","用户Token验证失败"),
    USER_NULL("B000200", "用户记录不存在"),
    USER_NAME_EXIST("B000201", "用户名已存在"),
    USER_EXIST("B000202", "用户记录已存在"),
    USER_INSERT_ERROR("B000203", "用户记录新增失败"),
    USER_IS_LOGIN("B000204", "用户已登录"),
    USER_NO_LOGIN_OR_NO_TOKEN("B000205","TOKEN不存在或者用户未登录");


    private final String code;

    private final String message;

    UserErrorCodeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
