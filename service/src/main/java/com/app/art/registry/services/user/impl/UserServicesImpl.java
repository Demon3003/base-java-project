package com.app.art.registry.services.user.impl;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.UserRepository;
import com.app.art.registry.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Service
public class UserServicesImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findById(BigInteger id) {
        return userRepository.findById(id).get();
    }
}
