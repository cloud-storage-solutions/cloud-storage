package org.cloud.storage.auth.controllers;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "com.auth0")
@Getter
@Setter
public class AuthConfiguration {
	@NotBlank
	private String domain;

	@NotBlank
	private String clientId;

	@NotBlank
	private String clientSecret;

	@Bean
	public FilterRegistrationBean filterRegistration() {
		final FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new Auth0Filter());
		registration.addUrlPatterns("/portal/*");
		registration.setName(Auth0Filter.class.getSimpleName());
		return registration;
	}

	public String getDomain() {
		return domain;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}
}
