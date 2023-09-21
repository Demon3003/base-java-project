package com.app.art.registry.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "permission_name")
    private String permissionName;

    @Transient // to ignore field for hibernate
    private String comment;

    Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String getAuthority() {
        return permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission='" + permissionName + '\'' +
                '}';
    }
}