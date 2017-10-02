package com.alimmit.ionic.chatclientauthorization.repository;

import com.alimmit.ionic.chatclientauthorization.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, String> {

    Iterable<UserAuthority> findByUsername(String username);

    @Query
    @Modifying
    void deleteByUsername(String username);
}
