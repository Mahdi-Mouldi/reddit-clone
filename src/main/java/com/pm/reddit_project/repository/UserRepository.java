package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
