package com.project.stuckunderflow.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stuckunderflow.entities.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>{

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
}
