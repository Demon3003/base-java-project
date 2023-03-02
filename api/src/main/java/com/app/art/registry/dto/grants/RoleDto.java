package com.app.art.registry.dto.grants;

import com.app.art.registry.dto.BaseDto;
import com.app.art.registry.model.user.Role;

public class RoleDto extends BaseDto<Role> {
    RoleDto(Role role) {
        super(role);
    }
}
