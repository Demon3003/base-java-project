package com.app.art.registry.services.user;

import com.app.art.registry.model.user.Role;
import com.app.art.registry.projection.user.RoleLightView;

import java.math.BigInteger;

public interface RoleService {

    Role createRole(Role r);

    Role updateRole(Role r);

    void deleteRoleById(BigInteger id);

    Role getRoleById(BigInteger id);

    RoleLightView getRoleLightById(BigInteger id);

    Role getRoleByName(String name);

    void addPermission(BigInteger roleId, BigInteger permissionId);

}
