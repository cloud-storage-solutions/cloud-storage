package org.cloud.storage.providers.dropbox;

import java.io.File;

import com.google.api.client.http.HttpResponse;

public interface CloudProvider {
	public HttpResponse upload(final File file, final String destination) throws Exception;

	public HttpResponse download();

	public HttpResponse getSpaceQuota() throws Exception;
}
