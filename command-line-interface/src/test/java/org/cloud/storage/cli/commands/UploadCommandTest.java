package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.UPLOAD_FILE_PATH;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.api.client.http.FileContent;

import http.factories.FileContentFactory;
import http.factories.RequestFactoryFacade;
import http.wrappers.HttpRequestWrapper;

@RunWith(MockitoJUnitRunner.class)
public class UploadCommandTest {
	private static final String POST_URL = "postUrl";
	private static final String DESTINATION = "destination";
	private static final File FILE = new File(DESTINATION);

	@Spy
	@InjectMocks
	private UploadCommand uploadCommand;

	@Mock
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Mock
	private UriComponentsBuilder uriComponentsBuilder;

	@Mock
	private UriComponents uriComponents;

	@Mock
	private RequestFactoryFacade requestFactoryFacade;

	@Mock
	private HttpRequestWrapper httpRequestWrapper;

	@Mock
	private FileContentFactory fileContentFactory;

	private FileContent fileContent = new FileContent(MediaType.APPLICATION_OCTET_STREAM_VALUE, FILE);

	@Before
	public void setUp() throws Exception {
		when(defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder()).thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.pathSegment(CLOUD_WORKER_REST_PATH, UPLOAD_FILE_PATH, DESTINATION))
				.thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.build()).thenReturn(uriComponents);
		when(uriComponents.toUriString()).thenReturn(POST_URL);

		doReturn(requestFactoryFacade).when(uploadCommand).createRequestFactoryFacade();
		when(fileContentFactory.createFileContent(FILE)).thenReturn(fileContent);
		when(requestFactoryFacade.createPostRequest(eq(POST_URL), eq(fileContent))).thenReturn(httpRequestWrapper);

		doReturn(DESTINATION).when(uploadCommand).getDestination();
		doReturn(FILE).when(uploadCommand).getFile();
	}

	@Test
	public void testRun() throws Exception {
		uploadCommand.run();

		verify(fileContentFactory).createFileContent(FILE);
		verify(uriComponentsBuilder).pathSegment(CLOUD_WORKER_REST_PATH, UPLOAD_FILE_PATH, DESTINATION);
		verify(requestFactoryFacade).createPostRequest(POST_URL, fileContent);
	}

	@Test(expected = IOException.class)
	public void testRunCreatePostRequestException() throws Exception {
		when(requestFactoryFacade.createPostRequest(POST_URL, fileContent)).thenThrow(IOException.class);

		uploadCommand.run();

		verify(fileContentFactory).createFileContent(FILE);
		verify(uriComponentsBuilder).pathSegment(CLOUD_WORKER_REST_PATH, UPLOAD_FILE_PATH, DESTINATION);
	}
}
