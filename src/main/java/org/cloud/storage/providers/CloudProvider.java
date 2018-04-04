package org.cloud.storage.providers;

import java.io.File;

public interface CloudProvider {
	void upload(final File file, final String destination) throws Exception;

	void download();

	String getSpaceQuota() throws Exception;
}
