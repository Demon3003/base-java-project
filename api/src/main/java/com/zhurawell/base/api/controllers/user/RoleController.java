package com.zhurawell.base.api.controllers.user;

import com.zhurawell.base.api.dto.grants.RoleDto;
import com.zhurawell.base.api.mappers.grants.RoleMapper;
import com.zhurawell.base.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasAuthority('role:manager') or hasAuthority('sysadm')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("/create")
    public ResponseEntity createRole(@RequestBody RoleDto role) {
        roleService.createRole(roleMapper.dtoToEntity(role));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateRole(@RequestBody RoleDto role) {
        roleService.updateRole(roleMapper.dtoToEntity(role));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sysadm')")
    public ResponseEntity deleteRole(@PathVariable("id") BigInteger id) {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(roleMapper.entityToDto(roleService.getRoleById(id)));
    }

    @PostMapping("/addPermission/{roleId}/{permissionId}")
    @PreAuthorize("hasAuthority('sysadm')")
    public ResponseEntity addPermission(@PathVariable("roleId") BigInteger roleId,
                                        @PathVariable("permissionId") BigInteger permissionId) {
        roleService.addPermission(roleId, permissionId);
        return ResponseEntity.ok().build();
    }
}
