package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.UPLOAD_FILE_PATH;

import java.io.IOException;

import org.cloud.storage.cli.executor.RunnableCommand;
import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

@Command(name = "upload", description = "Upload a file")
public class UploadCommand implements RunnableCommand {
	@Autowired
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Option(title = "Destination", name = { "-d", "--destination" }, description = "Location to a file for upload")
	private String destination;

	@Override
	public void run() throws IOException {
		UriComponents uriComponents = defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder()
				.pathSegment(CLOUD_WORKER_REST_PATH, UPLOAD_FILE_PATH, destination).build();
		createRequestFactoryFacade().createGetRequest(uriComponents.toUriString());
	}

}