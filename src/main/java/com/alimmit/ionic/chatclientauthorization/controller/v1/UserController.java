package com.alimmit.ionic.chatclientauthorization.controller.v1;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.alimmit.ionic.chatclientauthorization.service.UserService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);

    private final UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(Path.USER_SIGNUP)
    public ResponseEntity<OAuth2AccessToken> register(@RequestBody final User user) {

        userService.findOrCreate(user);

        // TODO Move out of here
        final RestTemplate template = new RestTemplate();
        HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

        template.setMessageConverters(Lists.newArrayList(formHttpMessageConverter, stringHttpMessageConverter, jacksonHttpMessageConverter));

        final String authorizationHeader = "Basic " + org.apache.commons.codec.binary.Base64.encodeBase64String("clientIdPassword:secret".getBytes());
        final MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Authorization", authorizationHeader);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("clientId", "clientIdPassword");
        body.add("username", user.getUsername());
        body.add("password", user.getPassword());

        LOG.debug(httpServletRequest.getScheme());
        LOG.debug(httpServletRequest.getServerName());
        LOG.debug(httpServletRequest.getServerPort());

        final String url = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/api/v1/user/login";
        LOG.debug(url);

        final HttpEntity entity = new HttpEntity(body, headers);
        return template.exchange(url, HttpMethod.POST, entity, OAuth2AccessToken.class);
        // TODO Move out of here
    }
}
