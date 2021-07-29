package com.codingkitts.happyhour.repos;

import com.codingkitts.happyhour.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    long deleteByUsername(String username);
}
