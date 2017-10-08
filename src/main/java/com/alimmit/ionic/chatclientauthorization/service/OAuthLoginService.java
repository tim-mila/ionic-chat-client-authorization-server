package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface OAuthLoginService {

    OAuth2AccessToken login(User user);
}
