package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
}
