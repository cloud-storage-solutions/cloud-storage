package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;

import java.io.IOException;

import org.cloud.storage.cli.executor.RunnableCommand;
import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

import com.github.rvesse.airline.annotations.Command;

import http.factories.RequestFactoryFacade;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand implements RunnableCommand {
	@Autowired
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Override
	public void run() throws IOException {
		UriComponents uriComponents = defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder()
				.pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH).build();
		createRequestFactoryFacade().createGetRequest(uriComponents.toUriString());
	}

	protected RequestFactoryFacade createRequestFactoryFacade() {
		return new RequestFactoryFacade();
	}

}