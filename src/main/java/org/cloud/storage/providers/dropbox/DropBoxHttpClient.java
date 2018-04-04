package org.cloud.storage.providers.dropbox;

import static org.cloud.storage.commons.http.HeaderConstants.APPLICATION_OCTET_STREAM;
import static org.cloud.storage.commons.http.HeaderConstants.DROPBOX_API;
import static org.cloud.storage.providers.dropbox.DropBoxEndpointConstants.DOWNLOAD_ENDPOINT;
import static org.cloud.storage.providers.dropbox.DropBoxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.cloud.storage.providers.dropbox.DropBoxEndpointConstants.UPLOAD_ENDPOINT;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.commons.http.HttpRequestWrapper;
import org.cloud.storage.commons.http.HttpResponseWrapper;
import org.cloud.storage.commons.http.factory.RequestFactory;
import org.cloud.storage.providers.CloudProvider;

import com.google.api.client.http.FileContent;

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
	public HttpResponseWrapper upload(final File file, final String destination) throws IOException {
		final HttpRequestWrapper httpRequestWrapper = requestFactory.createPostRequest(UPLOAD_ENDPOINT,
				createFileContent(file));
		httpRequestWrapper.getHeaders().set(DROPBOX_API,
				"{\"path\": \"/" + destination + "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false}");
		return executeRequest(httpRequestWrapper);
	}

	protected FileContent createFileContent(final File file) {
		return new FileContent(APPLICATION_OCTET_STREAM, file);
	}

	@Override
	public HttpResponseWrapper download(final String destination) throws IOException {
		final HttpRequestWrapper httpRequestWrapper = requestFactory.createPostRequest(DOWNLOAD_ENDPOINT);
		httpRequestWrapper.getHeaders().set(DROPBOX_API, "{\"path\": \"" + destination + "\"}");
		return executeRequest(httpRequestWrapper);
	}

	// expected 200
	// TODO: autoclosable response
	@Override
	public HttpResponseWrapper getSpaceQuota() throws IOException {
		final HttpRequestWrapper httpRequestWrapper = requestFactory.createPostRequest(SPACE_QUOTA_ENDPOINT);
		return executeRequest(httpRequestWrapper);
	}

	protected HttpResponseWrapper executeRequest(final HttpRequestWrapper httpRequestWrapper) throws IOException {
		return new HttpResponseWrapper(httpRequestWrapper.execute());
	}
}
