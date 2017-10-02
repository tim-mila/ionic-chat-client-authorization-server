package com.alimmit.ionic.chatclientauthorization.repository;

import com.alimmit.ionic.chatclientauthorization.ChatClientAuthorizationApplicationTests;
import com.alimmit.ionic.chatclientauthorization.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTests extends ChatClientAuthorizationApplicationTests {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void beforeTest() {
        try {
            user = userRepository.save(User.create("username", "secret"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void afterTest() {
        userRepository.delete(user);
    }

    @Test
    public void assertFindByUsername() throws Exception {
        Assert.assertEquals(user.getUsername(), userRepository.findByUsername("username").getUsername());
        Assert.assertNull(userRepository.findByUsername("foobar"));
    }
}
