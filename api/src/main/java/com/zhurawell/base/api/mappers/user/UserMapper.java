package com.zhurawell.base.api.mappers.user;

import com.zhurawell.base.api.dto.user.UserDto;
import com.zhurawell.base.api.dto.user.UserDtoLight;
import com.zhurawell.base.data.model.user.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDto(User user);

    User dtoToEntity(UserDto user);

    List<UserDto> entityListToDtoList(List<User> entities);

    List<User> dtoListToEntityList(List<UserDto> users);

    UserDtoLight entityToDtoLight(User user);

    User dtoToEntityLight(UserDtoLight user);

    List<UserDtoLight> entityListToDtoListLight(List<User> entities);

    List<User> dtoListToEntityListLight(List<UserDtoLight> users);

}
