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
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

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
                .antMatchers("/api" + Path.USER_SIGNUP, "/api" + Path.USER_LOGIN, "/oauth/**4").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();


//        http
//                .antMatcher("/**")
//                .authorizeRequests().antMatchers("/login**").permitAll()
//                .anyRequest().authenticated()//.and().httpBasic()
//                .and().csrf().csrfTokenRepository(csrfTokenRepository()).ignoringAntMatchers("/shutdown")
//                //.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
//                .and().logout().logoutSuccessUrl("/").permitAll();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
