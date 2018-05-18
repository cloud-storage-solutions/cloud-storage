package org.cloud.storage.worker.commons.parsers;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.cloud.storage.worker.providers.dropbox.DropBoxHttpClient;
import org.junit.Before;
import org.junit.Test;

public class SpaceQuotaParserIntegrationTest {
	private static final long SPACE_QUOTA_IN_BYTES = 1024L;
	private static final String SPACE_QUOTA_JSON = "{ \"allocation\": { \"allocated\": 1024} }";

	private SpaceQuotaJsonParser spaceQuotaParser;

	@Before
	public void setUp() {
		spaceQuotaParser = new SpaceQuotaJsonParser(SPACE_QUOTA_JSON);
	}

	@Test
	public void parseProviderSpaceQuoata() {
		assertThat(spaceQuotaParser.parseProviderSpaceQuoata(DropBoxHttpClient.class), equalTo(SPACE_QUOTA_IN_BYTES));
	}
}
