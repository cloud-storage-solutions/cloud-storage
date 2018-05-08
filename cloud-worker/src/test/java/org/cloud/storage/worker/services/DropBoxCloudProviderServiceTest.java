package org.cloud.storage.worker.services;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.cloud.storage.worker.commons.parsers.SpaceQuotaParser;
import org.cloud.storage.worker.providers.dropbox.DropBoxHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DropBoxCloudProviderServiceTest {
	private static final String DESTINATION = "destination";
	private static final File FILE = new File(DESTINATION);

	private CloudProviderService cloudProviderService;

	@Mock
	private DropBoxHttpClient dropBoxHttpClientMock;

	@Mock
	private SpaceQuotaParser spaceQuotaParserMock;

	@Before
	public void setUp() throws Exception {
		cloudProviderService = spy(new CloudProviderService(dropBoxHttpClientMock));

		doReturn(spaceQuotaParserMock).when(cloudProviderService).createSpaceQuotaParser();
	}

	@Test
	public void testUpload() throws Exception {
		cloudProviderService.upload(FILE, DESTINATION);
		verify(dropBoxHttpClientMock).upload(eq(FILE), eq(DESTINATION));
	}

	@Test
	public void testGetSpaceInKiloBytes() throws Exception {
		when(spaceQuotaParserMock.parseProviderSpaceQuoata(dropBoxHttpClientMock.getClass())).thenReturn(1024L);

		assertThat(cloudProviderService.getSpaceQuota(), equalTo("1 KB"));
	}

	@Test
	public void testGetSpaceInMegaBytes() throws Exception {
		when(spaceQuotaParserMock.parseProviderSpaceQuoata(dropBoxHttpClientMock.getClass()))
				.thenReturn((long) (1024 * 1024));

		assertThat(cloudProviderService.getSpaceQuota(), equalTo("1 MB"));
	}

	@Test
	public void testGetSpaceInGigaBytes() throws Exception {
		when(spaceQuotaParserMock.parseProviderSpaceQuoata(dropBoxHttpClientMock.getClass()))
				.thenReturn((long) (1024 * 1024 * 1024));

		assertThat(cloudProviderService.getSpaceQuota(), equalTo("1 GB"));
	}
}
