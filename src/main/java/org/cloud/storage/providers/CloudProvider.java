package org.cloud.storage.providers;

import java.io.File;

import org.cloud.storage.commons.http.HttpResponseWrapper;

public interface CloudProvider {
	HttpResponseWrapper upload(final File file, final String destination) throws Exception;

	HttpResponseWrapper download(final String destination) throws Exception;

	HttpResponseWrapper getSpaceQuota() throws Exception;
}
