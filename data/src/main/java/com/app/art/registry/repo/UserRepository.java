package com.app.art.registry.repo;

import com.app.art.registry.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    List<User> findByRegistrationDateAfter(Date afterDate);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrLogin(String email, String login);
}