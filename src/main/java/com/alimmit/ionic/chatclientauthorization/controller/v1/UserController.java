package com.alimmit.ionic.chatclientauthorization.controller.v1;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.alimmit.ionic.chatclientauthorization.service.OAuthLoginService;
import com.alimmit.ionic.chatclientauthorization.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Log LOG = LogFactory.getLog(UserController.class);

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(Path.USER_SIGN_UP)
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody final User user) {
        return userService.findOrCreate(user);
    }
}
