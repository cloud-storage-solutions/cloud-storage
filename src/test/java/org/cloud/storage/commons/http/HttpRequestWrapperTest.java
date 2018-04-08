package org.cloud.storage.commons.http;

import org.cloud.storage.commons.wrappers.HttpRequestWrapper;
import org.junit.Test;

import com.google.api.client.http.HttpRequest;

public class HttpRequestWrapperTest {
	private HttpRequest httpRequest;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWrapperFromNullRequest() {
		httpRequest = null;
		new HttpRequestWrapper(httpRequest);
	}
}
