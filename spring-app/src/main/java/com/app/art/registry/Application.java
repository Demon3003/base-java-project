package com.app.art.registry;

import com.app.art.registry.model.post.Post;
import com.app.art.registry.model.post.PostComment;
import com.app.art.registry.model.user.User;
import com.app.art.registry.projection.user.UserLightView;
import com.app.art.registry.repo.PostRepo;
import com.app.art.registry.repo.RoleRepository;
import com.app.art.registry.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CustomEntityDirtinessStrategy;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepo postRepo;

	@PersistenceContext
	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional(readOnly = false)
	public void run() {
		PostComment pc =  new PostComment();
		pc.setText("What a beautiful day!");
		Post p = postRepo.getById(BigInteger.valueOf(22l));
		pc.setPost(p);
		em.persist(pc);
	}
}
