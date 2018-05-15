package org.cloud.storage.worker.providers.dropbox;

import static org.cloud.storage.worker.commons.factories.DropboxAuthorizedRequestFactoryFacade.DROPBOX_AUTHORIZED_REQUEST_FACTORY_FACADE_QUALIFER;
import static org.cloud.storage.worker.providers.dropbox.constants.DropBoxHeaderConstants.DROPBOX_API;
import static org.cloud.storage.worker.providers.dropbox.constants.DropboxEndpointConstants.DOWNLOAD_ENDPOINT;
import static org.cloud.storage.worker.providers.dropbox.constants.DropboxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.cloud.storage.worker.providers.dropbox.constants.DropboxEndpointConstants.UPLOAD_ENDPOINT;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.worker.providers.HttpClientCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import http.factories.FileContentFactory;
import http.factories.RequestFactoryFacade;
import http.wrappers.HttpRequestWrapper;
import http.wrappers.HttpResponseWrapper;

@Component
public class DropBoxHttpClient implements HttpClientCloudProvider {
	@Autowired
	@Qualifier(DROPBOX_AUTHORIZED_REQUEST_FACTORY_FACADE_QUALIFER)
	private RequestFactoryFacade requestFactory;

	@Autowired
	private FileContentFactory fileContentFactory;

	/*
	 * 
	 * Do not use this to upload a file larger than 150 MB. Instead, create an
	 * upload session with upload_session/start.
	 */
	// expected 200
	@Override
	public HttpResponseWrapper upload(final File file, final String destination) throws IOException {
		final HttpRequestWrapper httpRequestWrapper = requestFactory.createPostRequest(UPLOAD_ENDPOINT,
				fileContentFactory.createFileContent(file));
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

	protected HttpResponseWrapper executeRequest(final HttpRequestWrapper httpRequestWrapper) throws IOException {
		return new HttpResponseWrapper(httpRequestWrapper.execute());
	}
}
