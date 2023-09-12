package com.app.art.registry.utils;

import com.app.art.registry.model.user.Role;
import com.app.art.registry.model.user.Status;
import com.app.art.registry.model.user.User;

import java.util.UUID;

public class UserUtil {

    public static User creatRandomUser() {
        User user = new User();
        Role role = new Role();
        role.setId(1l);
        user.setRole(role);
        user.setStatus(Status.ACTIVE);
        user.setLogin(UUID.randomUUID().toString());
        user.setPassword("111");
        user.setFirstName("TestBatch");
        user.setLastName("TestBatch");
        user.setEmail("lolkeke@gmail.com");
        user.setImage("Keek.cdd.com");
        user.setToken("dscsdcwrverfvwecdwecwewdc");
        return user;
    }
}
