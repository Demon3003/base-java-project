package com.app.art.registry.dto.grants;

import com.app.art.registry.dto.BaseDto;
import com.app.art.registry.model.user.Role;

public class RoleDto extends BaseDto<Role> {

    public RoleDto(Role role) {
        super(role);
    }

    public RoleDto() {
        super(new Role());
    }

    public Long getId() {
        return getPojo().getId();
    }

    public void setId(Long id) {
        getPojo().setId(id);
    }

    public String getName() {
        return getPojo().getName();
    }

    public void setName(String name) {
        getPojo().setName(name);
    }
}
