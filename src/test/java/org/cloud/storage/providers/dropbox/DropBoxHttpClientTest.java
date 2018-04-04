package org.cloud.storage.providers.dropbox;

import static org.cloud.storage.commons.http.HeaderConstants.APPLICATION_OCTET_STREAM;
import static org.cloud.storage.commons.http.HeaderConstants.DROPBOX_API;
import static org.cloud.storage.providers.dropbox.DropBoxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.cloud.storage.commons.http.HttpRequestWrapper;
import org.cloud.storage.commons.http.HttpResponseWrapper;
import org.cloud.storage.commons.http.factory.RequestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;

@RunWith(MockitoJUnitRunner.class)
public class DropBoxHttpClientTest {
	private static final String FILE_PATH = "filePath";
	private static final String FILE_DESTINATION = "file-destination";

	private static final String UPLOAD_HEADER_VALUE = "{\"path\": \"/" + FILE_DESTINATION
			+ "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false}";
	private static final String DOWNLOAD_HEADER_VALUE = "{\"path\": \"" + FILE_DESTINATION + "\"}";

	private static final File FILE = new File(FILE_PATH);

	private DropBoxHttpClient dropBoxHttpClient;

	@Mock
	private RequestFactory requestFactoryMock;

	@Mock
	private HttpRequestWrapper httpRequestWrapperMock;

	@Mock
	private HttpResponseWrapper httpResponseWrapperMock;

	@Mock
	private HttpHeaders httpHeaders;

	@Captor
	private ArgumentCaptor<File> fileArgumentCaptor;

	@Before
	public void setup() throws Exception {
		dropBoxHttpClient = spy(new DropBoxHttpClient(requestFactoryMock));

		when(requestFactoryMock.createPostRequest(anyString())).thenReturn(httpRequestWrapperMock);
		when(httpHeaders.set(anyString(), anyString())).thenReturn(httpHeaders);
		when(httpRequestWrapperMock.getHeaders()).thenReturn(httpHeaders);
		doReturn(httpResponseWrapperMock).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);
	}

	@Test
	public void testUpload() throws Exception {
		when(requestFactoryMock.createPostRequest(anyString(), any(HttpContent.class)))
				.thenReturn(httpRequestWrapperMock);
		doReturn(new FileContent(APPLICATION_OCTET_STREAM, FILE)).when(dropBoxHttpClient)
				.createFileContent(fileArgumentCaptor.capture());

		dropBoxHttpClient.upload(FILE, FILE_DESTINATION);

		verify(httpHeaders).set(DROPBOX_API, UPLOAD_HEADER_VALUE);
		assertThat(fileArgumentCaptor.getValue(), equalTo(FILE));
	}

	@Test
	public void testDownload() throws Exception {
		dropBoxHttpClient.download(FILE_DESTINATION);

		verify(httpHeaders).set(DROPBOX_API, DOWNLOAD_HEADER_VALUE);
	}

	@Test
	public void testGetSpaceQuota() throws Exception {
		dropBoxHttpClient.getSpaceQuota();

		verify(requestFactoryMock).createPostRequest(SPACE_QUOTA_ENDPOINT);
	}

}
