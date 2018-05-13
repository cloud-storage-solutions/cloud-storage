package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.UPLOAD_FILE_PATH;
import static lombok.AccessLevel.PROTECTED;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.cli.executor.RunnableCommand;
import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.springframework.web.util.UriComponents;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

import http.factories.FileContentFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PROTECTED)
@Getter
@Command(name = "upload", description = "Upload a file to desired location")
public class UploadCommand implements RunnableCommand {

	private final DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	private final FileContentFactory fileContentFactory;

	@Option(name = { "-f", "--file" }, description = "Location to a file for upload")
	private File file;

	@Option(name = { "-d", "--destination" }, description = "Destination for the uploaded file")
	private String destination;

	@Override
	public void run() throws IOException {
		UriComponents uriComponents = defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder()
				.pathSegment(CLOUD_WORKER_REST_PATH, UPLOAD_FILE_PATH, getDestination()).build();
		createRequestFactoryFacade().createPostRequest(uriComponents.toUriString(),
				fileContentFactory.createFileContent(getFile()));
	}

}