package org.cloud.storage.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;

import org.cloud.storage.commons.parsers.SpaceQuotaHttpResponseParser;
import org.cloud.storage.providers.CloudProvider;

import com.google.gson.JsonSyntaxException;

public class CloudProviderService {

	private final CloudProvider cloudProvider;

	public CloudProviderService(final CloudProvider cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	public void upload(final File file, final String destination) throws Exception {
		cloudProvider.upload(file, destination);
	}

	public String getSpaceQuota() throws JsonSyntaxException, Exception {
		long spaceQuota = new SpaceQuotaHttpResponseParser(cloudProvider.getSpaceQuota().parseAsString())
				.getParsedSpaceQuoata(cloudProvider.getClass());
		return byteCountToDisplaySize(spaceQuota);
	}

}