package com.codingkitts.happyhour.controllers;

import com.codingkitts.happyhour.entities.auth.User;
import com.codingkitts.happyhour.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping("/user")
    public User registerNewUser(@RequestBody User user) {
        return this.userService.registerNewUser(user);
    }

    @DeleteMapping("/user")
    public boolean deleteUser(Principal principal) {
        return this.userService.deleteUser(principal.getName());
    }
}
