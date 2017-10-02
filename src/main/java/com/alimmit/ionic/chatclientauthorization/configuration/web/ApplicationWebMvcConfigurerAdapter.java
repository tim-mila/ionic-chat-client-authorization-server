package com.alimmit.ionic.chatclientauthorization.configuration.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	private static final Log LOG = LogFactory.getLog(ApplicationWebMvcConfigurerAdapter.class);

	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		final ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
		registrationBean.addUrlMappings("/api/*", "/*");
		return registrationBean;
	}
}
