package com.app.art.registry.model.user;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

//    @Fetch(value = FetchMode.SELECT) // SELECT we use for LAZY  fetchType
//    @BatchSize(size = 4) // this annotation should be used with LAZY fetchType.
    @ManyToMany(fetch = FetchType.LAZY)         // It tells hibernate to load "permissions" objects not only for current object when we call getter but also for 4 other Role objects (if we have others objects in context)
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    public Set<Permission> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}