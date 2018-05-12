package org.cloud.storage.cli.commands.configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "cloud.worker.rest")
@PropertySource("classpath:/config/command.properties")
@Getter
@Setter
public class CommandConfiguration {
	@NotBlank
	@Pattern(regexp = "http|https")
	private String scheme;

	@NotBlank
	private String host;

	@NotBlank
	private int port;

}
