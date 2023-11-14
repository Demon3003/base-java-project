package com.app.art.registry;


import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.user.UserRepository;
import com.app.art.registry.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class ApplicationTests {

	@Autowired
	UserService userService;

	@MockBean
	UserRepository userRepository;

	@Test
	void contextLoads() {
		//Given
		User u = new User(BigInteger.valueOf(1l));
		when(userRepository.save(any(User.class))).thenReturn(u);

		//When
		u = userService.saveUser(u);

		//Then
		assertThat(u.getId()).isEqualTo(1l);
	}
}
