package com.app.art.registry.services.user;

import com.app.art.registry.model.user.User;

import java.math.BigInteger;

public interface UserService {

    public User findById(BigInteger id);
}
