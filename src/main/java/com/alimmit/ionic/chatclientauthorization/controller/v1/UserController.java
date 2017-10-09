package com.alimmit.ionic.chatclientauthorization.controller.v1;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.alimmit.ionic.chatclientauthorization.service.OAuthLoginService;
import com.alimmit.ionic.chatclientauthorization.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);

    private final UserService userService;
    private final OAuthLoginService oAuthLoginService;

    public UserController(final UserService userService, final OAuthLoginService oAuthLoginService) {
        this.userService = userService;
        this.oAuthLoginService = oAuthLoginService;
    }

    @PostMapping(Path.USER_SIGNUP)
    public ResponseEntity<OAuth2AccessToken> register(@RequestBody final User user) {
        final User u = userService.findOrCreate(user);
        return oAuthLoginService.login(u);
    }
}
