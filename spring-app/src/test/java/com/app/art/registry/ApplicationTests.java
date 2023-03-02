package com.app.art.registry;

import com.app.art.registry.model.user.DateAndImage;
import com.app.art.registry.model.user.Permission;
import com.app.art.registry.model.user.Role;
import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.UserRepository;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Transactional
	void testRepo() {
//		User user = userRepository.findById(BigInteger.ONE).get();
//		Role role = user.getRole();
//		Collection<Permission> perm = role.getPermissions();
//		Assert.isNull(user);
		DateAndImage dtm = userRepository.findUserRegistrationDateAndImage(BigInteger.valueOf(1l));
		Assert.isNull(dtm);
	}

}
