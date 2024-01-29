package com.zhurawell.base.api.exceptions;

public enum ErrorCodes {

    DEFAULT("100", "Default error"),
    C_3000("3000", "Token in blacklist.");

    private String code;

    private String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
