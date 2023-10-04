package com.app.art.registry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
public class Application {

//	@Autowired
//	private RoleRepository roleRepository;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private PostRepo postRepo;
//
//
//	@Autowired
//	private PostCommentRepo postComRepo;
//
//	@PersistenceContext
//	EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	@Transactional(readOnly = false)
//	public void run() {
//		PostComment pc = postComRepo.findById(BigInteger.ONE).get();
////		User u = userService.findById(BigInteger.ONE);
////		User u = userService.findByIdWithRole(BigInteger.ONE);
//	}

}
