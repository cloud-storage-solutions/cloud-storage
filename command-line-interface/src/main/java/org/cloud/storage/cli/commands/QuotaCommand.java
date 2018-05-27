package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;

import java.io.IOException;

import org.cloud.storage.cli.executor.RunnableCommand;
import org.cloud.storage.cli.utils.CloudWorkerInternalRestUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.rvesse.airline.annotations.Command;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand implements RunnableCommand {
	private CloudWorkerInternalRestUriComponentsBuilder cloudWorkerInternalRestUriComponentsBuilder = new CloudWorkerInternalRestUriComponentsBuilder();

	@Override
	public void run() throws IOException {
		UriComponentsBuilder buildDefaultInternalRestApiUri = cloudWorkerInternalRestUriComponentsBuilder
				.buildDefaultInternalRestApiUri();
		UriComponents uriComponents = buildDefaultInternalRestApiUri.pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH)
				.build();
		createRequestFactoryFacade().createGetRequest(uriComponents.toUriString());
	}
}