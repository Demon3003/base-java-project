package com.app.art.registry.repo;

import com.app.art.registry.model.user.DateAndImage;
import com.app.art.registry.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {

    List<User> findByRegistrationDateAfter(Date afterDate);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrLogin(String email, String login);

    @Query("select u.image from User u where u.id = :userId")
    String findUserImage(@Param("userId") BigInteger userId);

    @Query("select u.registrationDate from User u where u.id = ?1")
    Date findUserRegistrationDate(BigInteger userId);

    @Query("select new com.app.art.registry.model.user.DateAndImage(u.registrationDate, u.image) from User u where u.id = ?1")
    DateAndImage findUserRegistrationDateAndImage(BigInteger userId);

    @Query("update User u set u.image = :img where u.id = :userId")
    @Modifying // Need to use this annotation when ypu modify data
    void updateUserImage(@Param("userId") BigInteger userId, @Param("img") String img);
}