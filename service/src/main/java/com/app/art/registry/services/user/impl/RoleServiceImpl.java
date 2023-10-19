package com.app.art.registry.services.user.impl;

import com.app.art.registry.model.user.Permission;
import com.app.art.registry.model.user.Role;
import com.app.art.registry.projection.user.RoleLightView;
import com.app.art.registry.repo.user.PermissionRepository;
import com.app.art.registry.repo.user.RoleRepository;
import com.app.art.registry.services.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @Transactional
    public Role createRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    @Transactional
    public Role updateRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    @Transactional
    public void deleteRoleById(BigInteger id) {
        roleRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(BigInteger id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleLightView getRoleLightById(BigInteger id) {
        return roleRepo.getRoleByIdLight(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        return roleRepo.findRoleByNameWithPermissions(name);
    }

    @Override
    @Transactional
    public void addPermission(BigInteger roleId, BigInteger permissionId) {
        Role role = roleRepo.getById(roleId);
        Permission permission = permissionRepository.getById(permissionId);
        role.addPermission(permission);
    }

}
