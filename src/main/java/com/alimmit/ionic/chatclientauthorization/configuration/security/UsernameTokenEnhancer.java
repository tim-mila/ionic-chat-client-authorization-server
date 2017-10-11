package com.alimmit.ionic.chatclientauthorization.configuration.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class UsernameTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(final OAuth2AccessToken oAuth2AccessToken, final OAuth2Authentication oAuth2Authentication) {
            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("username", oAuth2Authentication.getUserAuthentication().getName());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
            return oAuth2AccessToken;
    }
}
