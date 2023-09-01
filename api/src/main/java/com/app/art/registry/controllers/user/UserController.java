package com.app.art.registry.controllers.user;

import com.app.art.registry.dto.user.UserDto;
import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.UserRepository;
import com.app.art.registry.services.user.UserService;
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
 * @author dimazhuravlyov
 * */
@Slf4j
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('all:write')")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setRegistrationDate(new Date());
        userRepository.save(user);
        log.debug("DMZH TEST: {}", user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userRepository.save(user);
        log.debug("DMZH TEST: {}", user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") BigInteger id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(new UserDto(userService.findById(id)));
    }

    /**
     * RSET endpoint to test functionality of the
     * @see  com.app.art.registry.converters.UserRestConverter
     * example of a request: http://localhost:8086/api/user/get/new/?user=Dmytro
     * */
    @GetMapping("/get/new/")
    public ResponseEntity<User> getUserNew(User user) {
        return ResponseEntity.ok(userRepository.findByLogin(user.getLogin()).get());
    }

    @GetMapping("/getAllActiveFrom/{date}")
    public ResponseEntity<List<User>> getUser(@PathVariable("date") Long activeFrom) {
        log.debug("Active from date: {}", new Date(1644463162597L));

        return ResponseEntity.ok(userRepository.findByRegistrationDateAfter(new Date(activeFrom)));
    }
}
