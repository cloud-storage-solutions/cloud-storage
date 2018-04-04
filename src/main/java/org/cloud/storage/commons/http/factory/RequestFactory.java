package org.cloud.storage.commons.http.factory;

import java.io.IOException;

import org.cloud.storage.commons.http.HttpRequestWrapper;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class RequestFactory {
	private final HttpRequestFactory requestFactory;

	public RequestFactory() {
		requestFactory = createAuthorizedRequestFactory(new ApacheHttpTransport());
	}

	public HttpRequestWrapper createGetRequest(final String url) throws IOException {
		return new HttpRequestWrapper(requestFactory.buildGetRequest(new GenericUrl(url)));
	}

	public HttpRequestWrapper createPostRequest(final String url) throws IOException {
		return createPostRequest(url, null);
	}

	public HttpRequestWrapper createPostRequest(final String url, final HttpContent httpContent) throws IOException {
		return new HttpRequestWrapper(requestFactory.buildPostRequest(new GenericUrl(url), httpContent));
	}

	private HttpRequestFactory createAuthorizedRequestFactory(final HttpTransport transport) {
		return transport.createRequestFactory(request -> request.getHeaders()
				.setAuthorization("Bearer j5jRToJdvHAAAAAAAABmwShwRVQJ3Fe8HIGqqAsqHFN9Uadp4WecU3WC2IBAva2Z"));
	}

}
