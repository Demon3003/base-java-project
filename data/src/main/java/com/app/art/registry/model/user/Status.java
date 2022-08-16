package com.app.art.registry.model.user;

public enum Status {

    ACTIVE("active"),
    INACTIVE("inactive");

    Status(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
