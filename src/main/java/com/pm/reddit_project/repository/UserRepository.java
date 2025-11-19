package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.User;
import com.pm.reddit_project.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String token);
}
