package com.alimmit.ionic.chatclientauthorization.repository;

import com.alimmit.ionic.chatclientauthorization.ChatClientAuthorizationApplicationTests;
import com.alimmit.ionic.chatclientauthorization.entity.UserAuthority;
import com.google.common.collect.Iterables;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class UserAuthorityRepositoryTests extends ChatClientAuthorizationApplicationTests {

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    private UserAuthority userAuthority;

    @Before
    public void beforeTest() {
        userAuthority = userAuthorityRepository.save(UserAuthority.createUser("username", "ROLE"));
    }

    @After
    public void afterTest() {
        userAuthorityRepository.delete(userAuthority);
    }

    @Test
    public void assertAuthorities() throws Exception {
        Assert.assertEquals(userAuthority.getAuthority(), userAuthorityRepository.findByUsername("username").iterator().next().getAuthority());
        Assert.assertTrue(Iterables.isEmpty(userAuthorityRepository.findByUsername("foobar")));
    }
}
