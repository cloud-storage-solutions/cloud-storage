package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;

import java.io.IOException;

import org.cloud.storage.cli.executor.AbstractRunnableCommand;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.rvesse.airline.annotations.Command;

import http.factories.RequestFactoryFacade;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand extends AbstractRunnableCommand {

	@Override
	public void run() throws IOException {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(commandConfiguration.getScheme())
				.host(commandConfiguration.getHost()).port(commandConfiguration.getPort())
				.pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH).build();
		new RequestFactoryFacade().createGetRequest(uriComponents.toUriString());
	}

}