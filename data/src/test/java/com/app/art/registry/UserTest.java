package com.app.art.registry;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DataJpaTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testGetById() {
        log.debug("DMZH: {}", userRepository);
        User u = new User(BigInteger.ONE);
        testEntityManager.persist(u);
        testEntityManager.flush();
        assertThat(userRepository.getById(BigInteger.ONE)).isNotEqualTo(new User(BigInteger.ONE));
    }

}
