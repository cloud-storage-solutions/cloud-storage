package org.cloud.storage.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;
import java.io.IOException;

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
		long spaceQuota = createSpaceQuotaHttpResponseParser().parseProviderSpaceQuoata(cloudProvider.getClass());
		return byteCountToDisplaySize(spaceQuota);
	}

	protected SpaceQuotaHttpResponseParser createSpaceQuotaHttpResponseParser() throws IOException, Exception {
		return new SpaceQuotaHttpResponseParser(cloudProvider.getSpaceQuota().parseAsString());

	}

}