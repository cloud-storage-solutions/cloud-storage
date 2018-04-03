package org.cloud.storage.providers;

import java.io.File;

import com.google.api.client.http.HttpResponse;

public interface CloudProvider {
	HttpResponse upload(final File file, final String destination) throws Exception;

	HttpResponse download();

	String getSpaceQuota() throws Exception;
}
