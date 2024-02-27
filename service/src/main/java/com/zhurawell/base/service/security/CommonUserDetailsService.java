package com.zhurawell.base.service.security;

import com.zhurawell.base.data.model.user.User;
import com.zhurawell.base.data.repo.user.UserRepository;
import com.zhurawell.base.service.exception.BuilderRuntimeException;
import com.zhurawell.base.service.exception.ErrorCodes;
import com.zhurawell.base.service.exception.CustomExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class CommonUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CommonUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws BuilderRuntimeException {
        User user = userRepository.findByEmailOrLogin(login, login).orElseThrow(() ->
                CustomExceptionHandler.getInstance()
                        .withErrorCode(ErrorCodes.C_101)
                        .withParams(login)
                        .build());
        return user.getUserDetails();
    }

}
