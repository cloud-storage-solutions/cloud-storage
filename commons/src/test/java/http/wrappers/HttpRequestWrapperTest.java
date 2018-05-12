package http.wrappers;

import org.junit.Test;

import com.google.api.client.http.HttpRequest;

public class HttpRequestWrapperTest {
	private HttpRequest httpRequest;

	@Test(expected = NullPointerException.class)
	public void testCreateWrapperFromNullRequest() {
		httpRequest = null;
		new HttpRequestWrapper(httpRequest);
	}
}
