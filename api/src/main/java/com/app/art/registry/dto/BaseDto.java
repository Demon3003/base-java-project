package com.app.art.registry.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseDto <T> {

    private T pojo;

    protected BaseDto(T pojo) {
        this.pojo = pojo;
    }

    @JsonIgnore
    public T getPojo() {
        return this.pojo;
    }
}
