package com.pm.reddit_project.repository;

import com.pm.reddit_project.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
