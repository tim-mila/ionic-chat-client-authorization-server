package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OAuthLoginServiceImpl implements OAuthLoginService {

    private static final Log LOG = LogFactory.getLog(OAuthLoginServiceImpl.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseEntity<OAuth2AccessToken> login(final User user) {

        final RestTemplate template = restTemplate();

        final String authorizationHeader = "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String("clientIdPassword:secret".getBytes());
        final MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);

        final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("clientId", "clientIdPassword");
        body.add("username", user.getUsername());
        body.add("password", user.getPassword());

        final String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/oauth/token";
        LOG.debug(url);

        final HttpEntity entity = new HttpEntity<>(body, headers);
        return template.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
    }

    private RestTemplate restTemplate() {
        final RestTemplate template = new RestTemplate();
        final HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        final HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        final List<HttpMessageConverter<?>> converters = Lists.<HttpMessageConverter<?>>newArrayList(formHttpMessageConverter, stringHttpMessageConverter, jacksonHttpMessageConverter);
        template.setMessageConverters(converters);
        return template;
    }
}
