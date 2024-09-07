package com.fullstack.jwt.service.user;

import com.fullstack.jwt.model.User;
import com.fullstack.jwt.repository.UserRepository;
import com.fullstack.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * When we pass user obj new user will be created
     * @param user
     * @return created user
     */
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
