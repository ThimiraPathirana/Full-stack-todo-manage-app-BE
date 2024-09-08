package com.fullstack.jwt.service.user;

import com.fullstack.jwt.model.User;
import com.fullstack.jwt.repository.UserRepository;
import com.fullstack.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    /**
     * When we pass user obj new user will be created
     * @param user
     * @return created user
     */
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    /**
     * When we pass user verify the user
     * @param user
     * @return Authentication
     */
    @Override
    public String verify(User user) {
        Authentication authentication =
                manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
        if (authentication.isAuthenticated())
            return "Success";
        return "fail";
    }
}
