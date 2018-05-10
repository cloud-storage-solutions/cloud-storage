package http.factories;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.apache.ApacheHttpTransport;

import http.wrappers.HttpRequestWrapper;

public class RequestFactoryFacade {
	private final HttpRequestFactory httpRequestFactory;

	public RequestFactoryFacade() {
		httpRequestFactory = new ApacheHttpTransport().createRequestFactory();
	}

	protected RequestFactoryFacade(final HttpRequestFactory httpRequestFactory) {
		this.httpRequestFactory = httpRequestFactory;
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

}
