package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuthLoginService {

    ResponseEntity<OAuth2AccessToken> login(User user);
}
