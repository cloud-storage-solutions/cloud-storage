package org.cloud.storage.cli.utils;

import org.cloud.storage.cli.commands.configuration.CommandConfiguration;
import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

public class CloudWorkerInternalRestUriComponentsBuilder {
	@Autowired
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Autowired
	private CommandConfiguration commandConfiguration;

	public UriComponentsBuilder buildDefaultInternalRestApiUri() {
		return defaultUriComponentsBuilderFactory.createUriComponentsBuilder().scheme(commandConfiguration.getScheme())
				.host(commandConfiguration.getHost()).port(commandConfiguration.getPort());
	}

}
