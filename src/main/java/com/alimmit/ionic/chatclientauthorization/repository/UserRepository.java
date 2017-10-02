package com.alimmit.ionic.chatclientauthorization.repository;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
