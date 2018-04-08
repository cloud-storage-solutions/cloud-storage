package org.cloud.storage.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.cloud.storage.commons.parsers.SpaceQuotaHttpResponseParser;
import org.cloud.storage.providers.dropbox.DropBoxHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DropBoxCloudProviderServiceTest {
	private static final long TEN_GIGABYTES_IN_BYTES = 10000000000L;
	private static final String DESTINATION = "destination";
	private static final File FILE = new File(DESTINATION);

	private CloudProviderService cloudProviderService;

	@Mock
	private DropBoxHttpClient dropBoxHttpClientMock;

	@Mock
	private SpaceQuotaHttpResponseParser spaceQuotaHttpResponseParser;

	@Before
	public void setUp() {
		cloudProviderService = spy(new CloudProviderService(dropBoxHttpClientMock));
	}

	@Test
	public void testUpload() throws Exception {
		cloudProviderService.upload(FILE, DESTINATION);
		verify(dropBoxHttpClientMock).upload(eq(FILE), eq(DESTINATION));
	}

	@Test
	public void testGetSpaceQuoataSuccessful() throws Exception, Exception {
		when(spaceQuotaHttpResponseParser.parseProviderSpaceQuoata(DropBoxHttpClient.class))
				.thenReturn(TEN_GIGABYTES_IN_BYTES);

		assertThat(spaceQuotaHttpResponseParser.parseProviderSpaceQuoata(DropBoxHttpClient.class),
				equalTo(TEN_GIGABYTES_IN_BYTES));
	}
}
