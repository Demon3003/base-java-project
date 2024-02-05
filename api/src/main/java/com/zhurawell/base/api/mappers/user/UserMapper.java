package com.zhurawell.base.api.mappers.user;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.dto.user.UserDtoLight;
import com.zhurawell.base.data.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    public abstract UserDto entityToDto(User user);

    @Mapping(target = "password",
            expression = "java(user.getPassword() == null ? null : passwordEncoder.encode(user.getPassword()))")
    public abstract User dtoToEntity(UserDto user);

    public abstract List<UserDto> entityListToDtoList(List<User> entities);

    public abstract List<User> dtoListToEntityList(List<UserDto> users);

    public abstract UserDtoLight entityToDtoLight(User user);

    public abstract User dtoToEntityLight(UserDtoLight user);

    public abstract List<UserDtoLight> entityListToDtoListLight(List<User> entities);

    public abstract List<User> dtoListToEntityListLight(List<UserDtoLight> users);

}
