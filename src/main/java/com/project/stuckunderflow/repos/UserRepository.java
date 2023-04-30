package com.project.stuckunderflow.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stuckunderflow.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUserName(String username);
}
