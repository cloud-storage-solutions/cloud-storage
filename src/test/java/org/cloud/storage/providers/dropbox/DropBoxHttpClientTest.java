package org.cloud.storage.providers.dropbox;

import static org.cloud.storage.providers.dropbox.DropBoxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.cloud.storage.commons.http.HttpRequestWrapper;
import org.cloud.storage.commons.http.HttpResponseWrapper;
import org.cloud.storage.commons.http.factory.RequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DropBoxHttpClientTest {
	private static final String RESPONSE_BODY_JSON = "{response-body-json-placeholder}";

	private DropBoxHttpClient dropBoxHttpClient;

	@Mock
	private RequestFactory requestFactoryMock;

	@Mock
	private HttpRequestWrapper httpRequestWrapperMock;

	@Mock
	private HttpResponseWrapper httpResponseWrapperMock;

	@Before
	public void setup() throws Exception {
		dropBoxHttpClient = spy(new DropBoxHttpClient(requestFactoryMock));
		when(requestFactoryMock.createPostRequest(anyString())).thenReturn(httpRequestWrapperMock);
		doReturn(httpResponseWrapperMock).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);
		when(httpResponseWrapperMock.parseAsString()).thenReturn(RESPONSE_BODY_JSON);
	}

	@Test
	public void testGetSpaceQuota() throws IOException {
		assertThat(dropBoxHttpClient.getSpaceQuota(), equalTo(RESPONSE_BODY_JSON));
		verify(requestFactoryMock).createPostRequest(SPACE_QUOTA_ENDPOINT);
	}

}
