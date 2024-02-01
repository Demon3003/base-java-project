package com.zhurawell.base.service.exception;

import lombok.Getter;

@Getter
public class BuilderRuntimeException extends RuntimeException {

    private String code;

    private String message;

    public BuilderRuntimeException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BuilderRuntimeException(String code, String message, Throwable ex) {
        super(ex);
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code = " + code +
                "message = " + message +
                '}';
    }
}
