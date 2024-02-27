package com.zhurawell.base.data.model.dashboard;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "dashboard")
@NoArgsConstructor
@SequenceGenerator(name = "dashboard_generator", schema = "public", sequenceName = "dashboard_sequence",
        initialValue = 5,
        allocationSize = 20)
public class Dashboard {

    @Id
    @GeneratedValue(generator = "dashboard_generator")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")
    private int type;

    @JoinColumn(name = "parent_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dashboard parent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dashboard user = (Dashboard) o;

        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Dashboard.class.hashCode();
    }

}
