package org.cloud.storage.commons.http;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import com.google.api.client.http.HttpRequest;

public class HttpRequestWrapperTest {
	private HttpRequest httpRequest;

	@Captor
	private ArgumentCaptor<HttpRequest> httpRequestCaptor;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWrapperFromNullRequest() {
		httpRequest = null;
		new HttpRequestWrapper(httpRequest);
	}
}
