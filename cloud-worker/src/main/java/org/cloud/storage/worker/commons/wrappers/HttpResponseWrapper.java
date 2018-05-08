package org.cloud.storage.worker.commons.wrappers;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;

public class HttpResponseWrapper {
	private final HttpResponse httpResponse;

	public HttpResponseWrapper(final HttpResponse httpResponse) {
		notNull(httpResponse, "HttpResponse param is null");
		this.httpResponse = httpResponse;
	}

	@Override
	public int hashCode() {
		return httpResponse.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return httpResponse.equals(obj);
	}

	public int getContentLoggingLimit() {
		return httpResponse.getContentLoggingLimit();
	}

	public HttpResponse setContentLoggingLimit(int contentLoggingLimit) {
		return httpResponse.setContentLoggingLimit(contentLoggingLimit);
	}

	public boolean isLoggingEnabled() {
		return httpResponse.isLoggingEnabled();
	}

	public HttpResponse setLoggingEnabled(boolean loggingEnabled) {
		return httpResponse.setLoggingEnabled(loggingEnabled);
	}

	public String getContentEncoding() {
		return httpResponse.getContentEncoding();
	}

	public String getContentType() {
		return httpResponse.getContentType();
	}

	public HttpMediaType getMediaType() {
		return httpResponse.getMediaType();
	}

	public HttpHeaders getHeaders() {
		return httpResponse.getHeaders();
	}

	public boolean isSuccessStatusCode() {
		return httpResponse.isSuccessStatusCode();
	}

	public int getStatusCode() {
		return httpResponse.getStatusCode();
	}

	public String getStatusMessage() {
		return httpResponse.getStatusMessage();
	}

	public HttpTransport getTransport() {
		return httpResponse.getTransport();
	}

	public HttpRequest getRequest() {
		return httpResponse.getRequest();
	}

	public InputStream getContent() throws IOException {
		return httpResponse.getContent();
	}

	@Override
	public String toString() {
		return httpResponse.toString();
	}

	public void download(OutputStream outputStream) throws IOException {
		httpResponse.download(outputStream);
	}

	public void ignore() throws IOException {
		httpResponse.ignore();
	}

	public void disconnect() throws IOException {
		httpResponse.disconnect();
	}

	public <T> T parseAs(Class<T> dataClass) throws IOException {
		return httpResponse.parseAs(dataClass);
	}

	public Object parseAs(Type dataType) throws IOException {
		return httpResponse.parseAs(dataType);
	}

	public String parseAsString() throws IOException {
		return httpResponse.parseAsString();
	}

	public Charset getContentCharset() {
		return httpResponse.getContentCharset();
	}
}