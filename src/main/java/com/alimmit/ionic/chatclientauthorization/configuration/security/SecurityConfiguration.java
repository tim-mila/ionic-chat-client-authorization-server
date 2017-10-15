package com.alimmit.ionic.chatclientauthorization.configuration.security;

import com.alimmit.ionic.chatclientauthorization.controller.v1.Path;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Log LOG = LogFactory.getLog(SecurityConfiguration.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOG.info("==============================================");
        LOG.info("** Configure Auth                           **");
        LOG.info("==============================================");
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        LOG.info("******************************************************");
        LOG.info("Configure HttpSecurity");
        LOG.info("******************************************************");

        http.authorizeRequests()
                .antMatchers(Path.API_V1_USER_SIGN_UP, "/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

}
