package com.app.art.registry.dto.user;

import com.app.art.registry.dto.BaseDto;
import com.app.art.registry.model.user.User;

public class UserDto extends BaseDto<User> {

    public UserDto(User user) {
        super(user);
    }
}
