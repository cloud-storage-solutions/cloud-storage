package org.cloud.storage.providers.dropbox;

import static org.cloud.storage.providers.dropbox.constants.DropBoxEndpointConstants.DOWNLOAD_ENDPOINT;
import static org.cloud.storage.providers.dropbox.constants.DropBoxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.cloud.storage.providers.dropbox.constants.DropBoxEndpointConstants.UPLOAD_ENDPOINT;
import static org.cloud.storage.providers.dropbox.constants.DropBoxHeaderConstants.APPLICATION_OCTET_STREAM;
import static org.cloud.storage.providers.dropbox.constants.DropBoxHeaderConstants.DROPBOX_API;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.commons.factories.RequestFactory;
import org.cloud.storage.commons.wrappers.HttpRequestWrapper;
import org.cloud.storage.commons.wrappers.HttpResponseWrapper;
import org.cloud.storage.providers.HttpClientCloudProvider;

import com.google.api.client.http.FileContent;

public class DropBoxHttpClient implements HttpClientCloudProvider {

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

	protected FileContent createFileContent(final File file) {
		return new FileContent(APPLICATION_OCTET_STREAM, file);
	}

	protected HttpResponseWrapper executeRequest(final HttpRequestWrapper httpRequestWrapper) throws IOException {
		return new HttpResponseWrapper(httpRequestWrapper.execute());
	}
}
