package org.cloud.storage.worker.commons.http;

import org.cloud.storage.worker.commons.wrappers.HttpRequestWrapper;
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
