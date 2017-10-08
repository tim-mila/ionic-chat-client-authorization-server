package com.alimmit.ionic.chatclientauthorization.service;

import com.alimmit.ionic.chatclientauthorization.entity.User;
import com.alimmit.ionic.chatclientauthorization.entity.UserAuthority;
import com.alimmit.ionic.chatclientauthorization.repository.UserAuthorityRepository;
import com.alimmit.ionic.chatclientauthorization.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Log LOG = LogFactory.getLog(UserServiceImpl.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            final UserRepository userRepository,
            final UserAuthorityRepository userAuthorityRepository,
            final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        return findByUsername(s);
    }

    @Override
    public User findOrCreate(final User user) {

        if (null != findByUsername(user.getName())) {
            throw new HttpServerErrorException(HttpStatus.CONFLICT);
        }

        userRepository.save(User.create(user.getName(), passwordEncoder.encode(user.getPassword())));
        userAuthorityRepository.save(UserAuthority.createUser(user.getUsername(), UserAuthority.ROLE_ADMIN));
        return user;
    }

    @Override
    public User findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void delete(final User user) {
        try {
            userAuthorityRepository.deleteInBatch(userAuthorityRepository.findByUsername(user.getUsername()));
            userRepository.delete(user);
        }
        catch(Exception e) {
            LOG.error("User delete error::" + e.getMessage());
        }
    }
}
