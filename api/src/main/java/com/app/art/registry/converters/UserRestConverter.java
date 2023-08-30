package com.app.art.registry.converters;

import com.app.art.registry.model.user.User;
import org.springframework.core.convert.converter.Converter;

public class UserRestConverter implements Converter<String, User> {
    @Override
    public User convert(String  login) {
        User u = new User();
        u.setLogin(login);
        return u;
    }
}
