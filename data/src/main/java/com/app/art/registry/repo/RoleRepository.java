package com.app.art.registry.repo;

import com.app.art.registry.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Long> {

    @Query("from Role r left join fetch r.permissions where r.name = ?1")
    Role findRoleByNameWithPermissions(String name);

}
