package com.codingkitts.happyhour.services;

import com.codingkitts.happyhour.entities.auth.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);

    boolean deleteUser(String username);

    List<User> getAllUsers();
}
