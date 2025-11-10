package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
