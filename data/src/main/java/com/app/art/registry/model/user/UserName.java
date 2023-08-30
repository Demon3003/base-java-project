package com.app.art.registry.model.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserName implements Serializable {
    private String firstName;

    private String lastName;
}
