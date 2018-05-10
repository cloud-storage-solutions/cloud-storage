package org.cloud.storage.worker.commons.factories;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

import http.factories.RequestFactoryFacade;

public class DropboxAuthorizedRequestFactoryFacade extends RequestFactoryFacade {
	public DropboxAuthorizedRequestFactoryFacade() {
		super(createAuthorizedRequestFactory(new ApacheHttpTransport()));
	}

	// TODO missing unit test and how the fuck should I test this shit ....
	public static HttpRequestFactory createAuthorizedRequestFactory(final HttpTransport transport) {
		return transport.createRequestFactory(request -> request.getHeaders()
				.setAuthorization("Bearer j5jRToJdvHAAAAAAAABmwShwRVQJ3Fe8HIGqqAsqHFN9Uadp4WecU3WC2IBAva2Z"));
	}
}