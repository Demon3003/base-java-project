package com.app.art.registry.dto.grants;

import com.app.art.registry.model.user.Permission;
import com.app.art.registry.model.user.Role;
import com.app.art.registry.dto.BaseDto;

import java.math.BigInteger;
import java.util.List;

public class RoleDto extends BaseDto<Role> {

    public RoleDto(Role role) {
        super(role);
    }

    public RoleDto() {
        super(new Role());
    }

    public BigInteger getId() {
        return getPojo().getId();
    }

    public void setId(BigInteger id) {
        getPojo().setId(id);
    }

    public String getName() {
        return getPojo().getName();
    }

    public void setName(String name) {
        getPojo().setName(name);
    }

    public List<PermissionDto> getPermissions() {
        return BaseDto.fromPojoCollection(this.getPojo().getPermissions(), PermissionDto.class);
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.getPojo().addPermissions(BaseDto.toPojoCollection(permissions));
    }
}
