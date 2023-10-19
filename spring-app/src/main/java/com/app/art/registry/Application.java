package com.app.art.registry;

import com.app.art.registry.model.post.Post;
import com.app.art.registry.repo.post.PostRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@SpringBootApplication
@Slf4j
public class Application {


	@Autowired
	private PostRepo postRepo;
//
//	@PersistenceContext
//	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void run() {
		String text = postRepo.getPostTextById(BigInteger.valueOf(22L));
		log.error("DMZH: {}", text);
	}

}
