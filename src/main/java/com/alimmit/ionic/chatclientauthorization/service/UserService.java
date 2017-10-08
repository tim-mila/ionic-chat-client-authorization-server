package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;

public interface UserService {

    User findByUsername(String username);

    User findOrCreate(User user);

    void delete(User user);
}
