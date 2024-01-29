package com.zhurawell.base.api.exceptions;

public class BaseException extends RuntimeException {

    private ErrorCodes code;

    public BaseException(ErrorCodes code) {
        super();
        this.code = code;
    }

    public ErrorCodes getCode() {
        return this.code;
    }

    @Override
    public String toString() { // TODO
        return "BaseException{" +
                "code=" + code +
                '}';
    }
}
