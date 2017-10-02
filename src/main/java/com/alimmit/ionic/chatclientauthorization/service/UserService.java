package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;

import java.security.Principal;

public interface UserService {

    User findByUsername(String username);

    Principal findOrCreate(Principal principal);

    void delete(User user);
}
