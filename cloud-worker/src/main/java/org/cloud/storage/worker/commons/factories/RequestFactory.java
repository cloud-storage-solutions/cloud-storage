package org.cloud.storage.worker.commons.factories;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

import http.wrappers.HttpRequestWrapper;

public class RequestFactory {
	private final HttpRequestFactory httpRequestFactory;

	public RequestFactory() {
		httpRequestFactory = createAuthorizedRequestFactory(new ApacheHttpTransport());
	}

	public HttpRequestWrapper createGetRequest(final String url) throws IOException {
		return new HttpRequestWrapper(httpRequestFactory.buildGetRequest(new GenericUrl(url)));
	}

	public HttpRequestWrapper createPostRequest(final String url) throws IOException {
		return createPostRequest(url, null);
	}

	public HttpRequestWrapper createPostRequest(final String url, final HttpContent httpContent) throws IOException {
		return new HttpRequestWrapper(httpRequestFactory.buildPostRequest(new GenericUrl(url), httpContent));
	}

	// TODO missing unit test
	protected HttpRequestFactory createAuthorizedRequestFactory(final HttpTransport transport) {
		return transport.createRequestFactory(request -> request.getHeaders()
				.setAuthorization("Bearer j5jRToJdvHAAAAAAAABmwShwRVQJ3Fe8HIGqqAsqHFN9Uadp4WecU3WC2IBAva2Z"));
	}

}
