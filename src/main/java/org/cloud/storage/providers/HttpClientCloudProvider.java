package org.cloud.storage.providers;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.commons.wrappers.HttpResponseWrapper;

public interface HttpClientCloudProvider {
	HttpResponseWrapper upload(final File file, final String destination) throws IOException;

	HttpResponseWrapper download(final String destination) throws IOException;

	HttpResponseWrapper getSpaceQuota() throws IOException;
}
