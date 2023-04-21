package com.priyajit.project.socialnetwork.service;

import com.priyajit.project.socialnetwork.model.User;
import com.priyajit.project.socialnetwork.model.AuthToken;
import com.priyajit.project.socialnetwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAllByIds(List<Long> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByUserIdOrHandle(Long userId, String handle) {
        return userRepository.findByIdOrHandle(userId, handle).orElseThrow();
    }

    @Override
    public User login(Long userId, String handle, String secret) {
        User user = getByUserIdOrHandle(userId, handle);

        if(passwordEncoder.matches(secret, user.getCredential().getSecret())){
            return user;
        }
        else{
            return null;
        }
    }
}
