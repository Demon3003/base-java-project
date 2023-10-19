package com.app.art.registry.repo.user;

import com.app.art.registry.model.user.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, BigInteger> {
}
