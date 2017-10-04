package com.alimmit.ionic.chatclientauthorization.controller.v1;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.alimmit.ionic.chatclientauthorization.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@RestController
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(value = Path.USER_LOGIN)
//    public Principal user(final Principal principal) throws Exception {
//        final UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
//
//        if (User.class.isAssignableFrom(authenticationToken.getDetails().getClass())) {
//            return (User) authenticationToken.getDetails();
//        }
//        else if (WebAuthenticationDetails.class.isAssignableFrom(authenticationToken.getDetails().getClass())) {
//            final User user = userService.findByUsername(principal.getName()).populateDetails((WebAuthenticationDetails) authenticationToken.getDetails());
//            ((UsernamePasswordAuthenticationToken) principal).setDetails(user);
//            return (User) ((UsernamePasswordAuthenticationToken) principal).getDetails();
//        }
//        else {
//            throw new InsufficientAuthenticationException("Invalid state, no user or WebAuthentication");
//        }
//    }

    @PostMapping(Path.USER_SIGNUP)
    public void register(@RequestBody final User user) {
        userService.findOrCreate(user);
    }
}
