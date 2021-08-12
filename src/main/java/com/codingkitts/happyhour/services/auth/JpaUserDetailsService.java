package com.codingkitts.happyhour.services.auth;

import com.codingkitts.happyhour.entities.auth.User;
import com.codingkitts.happyhour.models.security.CustomUserDetails;
import com.codingkitts.happyhour.repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username){
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication");
        User u = this.userRepository.findUserByUsername(username).orElseThrow(s);
        return new CustomUserDetails(u);
    }
}
