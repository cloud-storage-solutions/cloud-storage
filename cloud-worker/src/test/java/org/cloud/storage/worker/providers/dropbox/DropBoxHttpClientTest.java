package org.cloud.storage.worker.providers.dropbox;

import static org.cloud.storage.worker.providers.dropbox.constants.DropBoxEndpointConstants.SPACE_QUOTA_ENDPOINT;
import static org.cloud.storage.worker.providers.dropbox.constants.DropBoxHeaderConstants.APPLICATION_OCTET_STREAM;
import static org.cloud.storage.worker.providers.dropbox.constants.DropBoxHeaderConstants.DROPBOX_API;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.worker.commons.factories.RequestFactory;
import org.cloud.storage.worker.commons.wrappers.HttpRequestWrapper;
import org.cloud.storage.worker.commons.wrappers.HttpResponseWrapper;
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
	private HttpHeaders httpHeadesMock;

	@Captor
	private ArgumentCaptor<File> fileArgumentCaptor;

	@Before
	public void setup() throws Exception {
		dropBoxHttpClient = spy(new DropBoxHttpClient(requestFactoryMock));

		when(requestFactoryMock.createPostRequest(anyString())).thenReturn(httpRequestWrapperMock);
		when(httpHeadesMock.set(anyString(), anyString())).thenReturn(httpHeadesMock);
		when(httpRequestWrapperMock.getHeaders()).thenReturn(httpHeadesMock);
		doReturn(httpResponseWrapperMock).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);

		when(requestFactoryMock.createPostRequest(anyString(), any(HttpContent.class)))
				.thenReturn(httpRequestWrapperMock);
		doReturn(new FileContent(APPLICATION_OCTET_STREAM, FILE)).when(dropBoxHttpClient)
				.createFileContent(fileArgumentCaptor.capture());
	}

	@Test
	public void testUpload() throws Exception {
		dropBoxHttpClient.upload(FILE, FILE_DESTINATION);

		verify(httpHeadesMock).set(DROPBOX_API, UPLOAD_HEADER_VALUE);
		assertThat(fileArgumentCaptor.getValue(), equalTo(FILE));
	}

	@Test(expected = IOException.class)
	public void testUploadRequestExecutionException() throws Exception {
		doThrow(IOException.class).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);

		dropBoxHttpClient.upload(any(File.class), anyString());
	}

	@Test
	public void testDownload() throws Exception {
		dropBoxHttpClient.download(FILE_DESTINATION);

		verify(httpHeadesMock).set(DROPBOX_API, DOWNLOAD_HEADER_VALUE);
	}

	@Test(expected = IOException.class)
	public void testDownloadRequestExecutionException() throws Exception {
		doThrow(IOException.class).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);

		dropBoxHttpClient.download(anyString());
	}

	@Test
	public void testGetSpaceQuota() throws Exception {
		dropBoxHttpClient.getSpaceQuota();

		verify(requestFactoryMock).createPostRequest(SPACE_QUOTA_ENDPOINT);
	}

	@Test(expected = IOException.class)
	public void testGetSpaceQuotaRequestExecutionException() throws Exception {
		doThrow(IOException.class).when(dropBoxHttpClient).executeRequest(httpRequestWrapperMock);

		dropBoxHttpClient.getSpaceQuota();
	}
}
