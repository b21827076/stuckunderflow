package com.project.stuckunderflow.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stuckunderflow.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByUserId(Long userId);
}
