package http.factories;

import static http.BasicHeaderConstants.APPLICATION_OCTET_STREAM;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;

import http.wrappers.HttpRequestWrapper;

public class RequestFactoryFacadeIntegrationTest {
	private static final String URL = "https://url.com";
	private static final String FILE_NAME = "file-name";
	private static final File FILE = new File(FILE_NAME);

	private RequestFactoryFacade requestFactory;
	private HttpRequestWrapper httpRequestWrapper;

	@Before
	public void setUp() {
		requestFactory = new RequestFactoryFacade();
	}

	@Test
	public void testCreateGetRequest() throws Exception {
		httpRequestWrapper = requestFactory.createGetRequest(URL);

		assertUrl(httpRequestWrapper.getUrl());
	}

	@Test
	public void testCreatePostRequestWithHttpContent() throws Exception {
		httpRequestWrapper = requestFactory.createPostRequest(URL, new FileContent(APPLICATION_OCTET_STREAM, FILE));

		final FileContent fileContent = (FileContent) httpRequestWrapper.getContent();

		assertUrl(httpRequestWrapper.getUrl());
		assertThat(fileContent.getType(), equalTo(APPLICATION_OCTET_STREAM));
		assertThat(fileContent.getFile(), equalTo(FILE));
	}

	@Test
	public void testCreatePostRequestWithoutHttpContent() throws Exception {
		httpRequestWrapper = requestFactory.createPostRequest(URL);

		assertUrl(httpRequestWrapper.getUrl());
		assertNull(httpRequestWrapper.getContent());
	}

	private void assertUrl(final GenericUrl genericUrl) {
		assertThat(genericUrl, equalTo(new GenericUrl(URL)));
	}
}
