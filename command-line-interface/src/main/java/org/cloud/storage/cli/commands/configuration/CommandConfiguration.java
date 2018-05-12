package org.cloud.storage.cli.commands.configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "cloud.worker.rest")
@PropertySource("classpath:/config/command.properties")
public class CommandConfiguration {
	@NotBlank
	@Pattern(regexp = "http|https")
	private String scheme;

	@NotBlank
	private String host;

	@NotBlank
	private int port;

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
