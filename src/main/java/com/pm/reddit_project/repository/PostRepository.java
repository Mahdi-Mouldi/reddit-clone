package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
