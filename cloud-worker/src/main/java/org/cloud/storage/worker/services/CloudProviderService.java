package org.cloud.storage.worker.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.worker.commons.parsers.SpaceQuotaParser;
import org.cloud.storage.worker.providers.HttpClientCloudProvider;

public class CloudProviderService {

	private final HttpClientCloudProvider cloudProvider;

	public CloudProviderService(final HttpClientCloudProvider cloudProvider) {
		this.cloudProvider = cloudProvider;
	}

	public void upload(final File file, final String destination) throws IOException {
		cloudProvider.upload(file, destination);
	}

	public String getSpaceQuota() throws IOException {
		long spaceQuota = createSpaceQuotaParser().parseProviderSpaceQuoata(cloudProvider.getClass());
		return byteCountToDisplaySize(spaceQuota);
	}

	protected SpaceQuotaParser createSpaceQuotaParser() throws IOException {
		return new SpaceQuotaParser(cloudProvider.getSpaceQuota().parseAsString());
	}

}