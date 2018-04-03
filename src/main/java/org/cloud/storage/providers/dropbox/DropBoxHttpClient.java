package org.cloud.storage.providers.dropbox;

import static org.cloud.storage.commons.http.HeaderConstants.DROPBOX_API;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.commons.http.factory.RequestFactory;
import org.cloud.storage.providers.CloudProvider;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;

public class DropBoxHttpClient implements CloudProvider {

	private final RequestFactory requestFactory;

	public DropBoxHttpClient() {
		requestFactory = new RequestFactory();
	}

	DropBoxHttpClient(final RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	/*
	 * 
	 * Do not use this to upload a file larger than 150 MB. Instead, create an
	 * upload session with upload_session/start.
	 */
	// expected 200
	@Override
	public HttpResponse upload(final File file, final String destination) throws IOException {
		final HttpRequest httpRequest = requestFactory.createPostRequest(
				"https://content.dropboxapi.com/2/files/upload", new FileContent("application/octet-stream", file));
		httpRequest.getHeaders().set(DROPBOX_API,
				"{\"path\": \"/" + destination + "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false}");
		return httpRequest.execute();
	}

	@Override
	public HttpResponse download() {
		// TODO Auto-generated method stub
		return null;
	}

	// expected 200
	// TODO: autoclosable response
	@Override
	public HttpResponse getSpaceQuota() throws IOException {
		final HttpRequest httpRequest = requestFactory
				.createPostRequest("https://api.dropboxapi.com/2/users/get_space_usage");
		return httpRequest.execute();
	}

}
