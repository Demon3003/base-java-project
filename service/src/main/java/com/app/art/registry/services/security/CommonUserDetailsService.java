package com.app.art.registry.services.security;

import com.app.art.registry.model.user.User;
import com.app.art.registry.repo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("CommonUserDetailsService")
public class CommonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CommonUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrLogin(login, login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return user.getUserDetails();
    }

}
