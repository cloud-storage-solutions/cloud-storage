package org.cloud.storage.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.commons.parsers.SpaceQuotaParser;
import org.cloud.storage.providers.HttpClientCloudProvider;

import com.google.gson.JsonSyntaxException;

public class CloudProviderService {

	private final HttpClientCloudProvider cloudProvider;

	public CloudProviderService(final HttpClientCloudProvider cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	public void upload(final File file, final String destination) throws Exception {
		cloudProvider.upload(file, destination);
	}

	public String getSpaceQuota() throws JsonSyntaxException, Exception {
		long spaceQuota = createSpaceQuotaHttpResponseParser().parseProviderSpaceQuoata(cloudProvider.getClass());
		return byteCountToDisplaySize(spaceQuota);
	}

	protected SpaceQuotaParser createSpaceQuotaHttpResponseParser() throws IOException, Exception {
		return new SpaceQuotaParser(cloudProvider.getSpaceQuota().parseAsString());

	}

}