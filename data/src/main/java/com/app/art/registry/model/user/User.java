package com.app.art.registry.model.user;

import com.app.art.registry.model.converter.user.UserNameConverter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String firstName;

    private String lastName;

    //    @Convert(converter = UserNameConverter.class)
    //    private UserName lastName;

    private String login;

    private String password;

    private String email;

    private String image;

    private Date registrationDate;

    private String token;

    public User(BigInteger id) {
        this.id = id;
    }

    @Column(name = "status_id")
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @Fetch(value = FetchMode.JOIN) // JOIN and SUBSELECT we use for EAGER  fetchType
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserDetails getUserDetails() {
        return new org.springframework.security.core.userdetails.User(
                this.getLogin(), this.getPassword(),
                this.getStatus().equals(Status.ACTIVE),
                this.getStatus().equals(Status.ACTIVE),
                this.getStatus().equals(Status.ACTIVE),
                this.getStatus().equals(Status.ACTIVE),
                this.getRole().getAuthorities()
        );
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                ", role=" + role.getName() +
                '}';
    }
}
