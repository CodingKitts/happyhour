package com.codingkitts.happyhour.services.impl;

import com.codingkitts.happyhour.entities.auth.User;
import com.codingkitts.happyhour.repos.UserRepository;
import com.codingkitts.happyhour.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerNewUser(User user) {
        if (this.userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            return null;
        }
        User tmpUser = new User();
        tmpUser.setUsername(user.getUsername());
        tmpUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(tmpUser);
    }

    @Override
    public boolean deleteUser(String username) {
        if (this.userRepository.findUserByUsername(username).isPresent()) {
            return this.userRepository.deleteByUsername(username) > 0;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
