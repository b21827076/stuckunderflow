package com.project.stuckunderflow.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stuckunderflow.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByUserId(Long userId);

    @Query(value = "select id from post where user_id = :userId order by create_date desc limit 5 ",
            nativeQuery = true)
    List<Long> findTopByUserId(@Param("userId") Long userId);
}
