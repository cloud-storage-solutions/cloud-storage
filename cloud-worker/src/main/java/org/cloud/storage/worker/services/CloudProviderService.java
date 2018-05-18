package org.cloud.storage.worker.services;

import static org.apache.commons.io.FileUtils.byteCountToDisplaySize;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.worker.commons.parsers.SpaceQuotaJsonParser;
import org.cloud.storage.worker.providers.HttpClientCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudProviderService {

	@Autowired
	private HttpClientCloudProvider cloudProvider;

	public void upload(final File file, final String destination) throws IOException {
		cloudProvider.upload(file, destination);
	}

	public String getSpaceQuota() throws IOException {
		final long spaceQuota = createSpaceQuotaParser().parseProviderSpaceQuoata(cloudProvider.getClass());
		return byteCountToDisplaySize(spaceQuota);
	}

	public void download(String filePath) throws IOException {
		cloudProvider.download(filePath);
	}

	protected SpaceQuotaJsonParser createSpaceQuotaParser() throws IOException {
		return new SpaceQuotaJsonParser(cloudProvider.getSpaceQuota().parseAsString());
	}

}