package com.app.art.registry;

import com.app.art.registry.model.post.PostComment;
import com.app.art.registry.model.post.PostDetails;
import com.app.art.registry.model.user.Role;
import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.post.PostRepository;
import com.app.art.registry.repo.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Application {


	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;
//
//	@PersistenceContext
//	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void run() {
//		PostLight text = postRepo.getPostLight(BigInteger.valueOf(22L));
//		PostLightExtended text = postRepo.getPostMainInfo(BigInteger.valueOf(22L));
//		log.error("DMZH: {}", pd.get(0));
	}

}
