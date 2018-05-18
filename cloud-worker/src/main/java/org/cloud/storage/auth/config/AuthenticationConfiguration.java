package org.cloud.storage.auth.config;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:/config/auth0.properties")
@Getter
@Setter
public class AuthenticationConfiguration {
	@NotBlank
	private String domain;

	@NotBlank
	private String clientId;

	@NotBlank
	private String clientSecret;
}
