package http.wrappers;

import org.junit.Test;

import com.google.api.client.http.HttpResponse;

public class HttpResponseWrapperTest {
	private HttpResponse httpResponse;

	@Test(expected = NullPointerException.class)
	public void testCreateWrapperFromNullRequest() {
		httpResponse = null;
		new HttpResponseWrapper(httpResponse);
	}
}
