package com.app.art.registry.controllers.user;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * REST controller to manage data about users.
 * */
@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('all:write')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        user.setRegistrationDate(new Date());
        userRepository.save(user);
        log.debug("DMZH TEST: {}", user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
    }

    @GetMapping("/getAllActiveFrom/{date}")
    public ResponseEntity<List<User>> getUser(@PathVariable("date") Long activeFrom) {
        log.debug("Active from date: {}", new Date(1644463162597L));

        return ResponseEntity.ok(userRepository.findByRegistrationDateAfter(new Date(activeFrom)));
    }
}
