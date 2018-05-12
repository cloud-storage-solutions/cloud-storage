package org.cloud.storage.cli.factories;

import org.cloud.storage.cli.commands.configuration.CommandConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DefaultUriComponentsBuilderFactory {
	@Autowired
	private CommandConfiguration commandConfiguration;

	public UriComponentsBuilder createDefaultUriComponentsBuilder() {
		return createUriComponentsBuilder().scheme(commandConfiguration.getScheme())
				.host(commandConfiguration.getHost()).port(commandConfiguration.getPort());
	}

	protected UriComponentsBuilder createUriComponentsBuilder() {
		return UriComponentsBuilder.newInstance();
	}
}
