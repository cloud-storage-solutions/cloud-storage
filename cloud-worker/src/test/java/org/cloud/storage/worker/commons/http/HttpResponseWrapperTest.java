package org.cloud.storage.worker.commons.http;

import org.cloud.storage.worker.commons.wrappers.HttpResponseWrapper;
import org.junit.Test;

import com.google.api.client.http.HttpResponse;

public class HttpResponseWrapperTest {
	private HttpResponse httpResponse;

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWrapperFromNullRequest() {
		httpResponse = null;
		new HttpResponseWrapper(httpResponse);
	}
}
