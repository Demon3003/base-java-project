package com.app.art.registry.controllers.user;

import com.app.art.registry.dto.grants.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grants")
public class GrantsController {

    @PostMapping("/createRole")
    public ResponseEntity createRole(RoleDto role) { // TODO check is it necessary to use @RequestBody
        return null;
    }

    @PutMapping("/addPermission")
    public ResponseEntity addPermission(RoleDto role) { // TODO check is it necessary to use @RequestBody
        return null;
    }
}
