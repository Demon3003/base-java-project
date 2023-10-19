package com.app.art.registry.services.user;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.user.UserRepository;
import com.app.art.registry.services.user.impl.UserServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {UserServicesImpl.class})
public class UserServiceTest {

    @Autowired
    private UserService userServices;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    EntityManager em;

    @MockBean
    EntityManagerFactory emf;

    @BeforeEach
    public void setUpData() {
        when(userRepository.save(any())).thenReturn(new User(BigInteger.ONE));
    }

    @Test
    public void test_save() {
        assertThat(userServices.saveUser(new User(BigInteger.ONE))).isEqualTo(new User(BigInteger.ONE));
    }

}
