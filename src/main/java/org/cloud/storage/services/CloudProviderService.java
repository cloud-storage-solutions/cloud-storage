package org.cloud.storage.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;

import org.cloud.storage.providers.dropbox.CloudProvider;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class CloudProviderService {

	private final CloudProvider cloudProvider;

	public CloudProviderService(final CloudProvider cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	public void upload(final File file, String destination) throws Exception {
		cloudProvider.upload(file, destination);
	}

	public String getSpaceQuota() throws JsonSyntaxException, Exception {
		long spaceQuota = new JsonParser().parse(cloudProvider.getSpaceQuota().parseAsString()).getAsJsonObject()
				.get("allocation").getAsJsonObject().get("allocated").getAsLong();
		return byteCountToDisplaySize(spaceQuota);
	}

}
