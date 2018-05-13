package org.cloud.storage.cli.commands;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.cloud.storage.cli.factories.DefaultUriComponentsBuilderFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import http.factories.RequestFactoryFacade;
import http.wrappers.HttpRequestWrapper;

@RunWith(MockitoJUnitRunner.class)
public class QuotaCommandTest {
	private static final String GET_URL = "getUrl";

	@Spy
	@InjectMocks
	private QuotaCommand quotaCommand;

	@Mock
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Mock
	private RequestFactoryFacade requestFactoryFacade;

	@Mock
	private HttpRequestWrapper httpRequestWrapper;

	@Mock
	private UriComponentsBuilder uriComponentsBuilder;

	@Mock
	private UriComponents uriComponents;

	@Before
	public void setUp() throws Exception {
		when(defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder()).thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH)).thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.build()).thenReturn(uriComponents);
		when(uriComponents.toUriString()).thenReturn(GET_URL);

		doReturn(requestFactoryFacade).when(quotaCommand).createRequestFactoryFacade();
		when(requestFactoryFacade.createGetRequest(GET_URL)).thenReturn(httpRequestWrapper);
	}

	@Test
	public void testRun() throws Exception {
		quotaCommand.run();

		verify(uriComponentsBuilder).pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH);
		verify(requestFactoryFacade).createGetRequest(GET_URL);
	}

	@Test(expected = IOException.class)
	public void testRunCreateGetRequestException() throws Exception {
		when(requestFactoryFacade.createGetRequest(GET_URL)).thenThrow(IOException.class);

		quotaCommand.run();

		verify(uriComponentsBuilder).pathSegment(CLOUD_WORKER_REST_PATH, QUOTA_PATH);
	}
}