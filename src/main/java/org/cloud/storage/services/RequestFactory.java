package org.cloud.storage.services;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class RequestFactory {
	private HttpRequestFactory requestFactory;

	public RequestFactory() {
		requestFactory = createAuthorizedRequestFactory(new ApacheHttpTransport());
	}

	public HttpRequest createGetRequest(final String url) throws IOException {
		return requestFactory.buildGetRequest(new GenericUrl(url));
	}

	public HttpRequest createPostRequest(final String url) throws IOException {
		return createPostRequest(url, null);
	}

	public HttpRequest createPostRequest(final String url, final HttpContent httpContent) throws IOException {
		return requestFactory.buildPostRequest(new GenericUrl(url), httpContent);
	}

	private HttpRequestFactory createAuthorizedRequestFactory(final HttpTransport transport) {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest request) throws IOException {
				request.getHeaders()
						.setAuthorization("Bearer mOr7MpfnLxAAAAAAAAAAHnno4DFivMbdVKyzgislw3Fx-kkH31auhhAsfRRasdPv");
			}
		});
	}

}
