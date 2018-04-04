package org.cloud.storage.commons.http;

import org.junit.Test;

import com.google.api.client.http.HttpResponse;

public class HttpResponseWrapperTest {
	private HttpResponse httpResponse;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWrapperFromNullRequest() {
		httpResponse = null;
		new HttpResponseWrapper(httpResponse);
	}

	// TODO: test successful path
}
