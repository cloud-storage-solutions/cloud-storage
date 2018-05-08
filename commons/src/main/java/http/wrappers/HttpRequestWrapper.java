package http.wrappers;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import com.google.api.client.http.BackOffPolicy;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpEncoding;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpIOExceptionHandler;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseInterceptor;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Sleeper;

@SuppressWarnings("deprecation")
public class HttpRequestWrapper {
	private final HttpRequest httpRequest;

	public HttpRequestWrapper(final HttpRequest httpRequest) {
		notNull(httpRequest, "HttpRequest param is null");
		this.httpRequest = httpRequest;
	}

	@Override
	public int hashCode() {
		return httpRequest.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return httpRequest.equals(obj);
	}

	public HttpTransport getTransport() {
		return httpRequest.getTransport();
	}

	public String getRequestMethod() {
		return httpRequest.getRequestMethod();
	}

	public HttpRequest setRequestMethod(String requestMethod) {
		return httpRequest.setRequestMethod(requestMethod);
	}

	public GenericUrl getUrl() {
		return httpRequest.getUrl();
	}

	public HttpRequest setUrl(GenericUrl url) {
		return httpRequest.setUrl(url);
	}

	public HttpContent getContent() {
		return httpRequest.getContent();
	}

	public HttpRequest setContent(HttpContent content) {
		return httpRequest.setContent(content);
	}

	public HttpEncoding getEncoding() {
		return httpRequest.getEncoding();
	}

	public HttpRequest setEncoding(HttpEncoding encoding) {
		return httpRequest.setEncoding(encoding);
	}

	public BackOffPolicy getBackOffPolicy() {
		return httpRequest.getBackOffPolicy();
	}

	public HttpRequest setBackOffPolicy(BackOffPolicy backOffPolicy) {
		return httpRequest.setBackOffPolicy(backOffPolicy);
	}

	@Override
	public String toString() {
		return httpRequest.toString();
	}

	public int getContentLoggingLimit() {
		return httpRequest.getContentLoggingLimit();
	}

	public HttpRequest setContentLoggingLimit(int contentLoggingLimit) {
		return httpRequest.setContentLoggingLimit(contentLoggingLimit);
	}

	public boolean isLoggingEnabled() {
		return httpRequest.isLoggingEnabled();
	}

	public HttpRequest setLoggingEnabled(boolean loggingEnabled) {
		return httpRequest.setLoggingEnabled(loggingEnabled);
	}

	public boolean isCurlLoggingEnabled() {
		return httpRequest.isCurlLoggingEnabled();
	}

	public HttpRequest setCurlLoggingEnabled(boolean curlLoggingEnabled) {
		return httpRequest.setCurlLoggingEnabled(curlLoggingEnabled);
	}

	public int getConnectTimeout() {
		return httpRequest.getConnectTimeout();
	}

	public HttpRequest setConnectTimeout(int connectTimeout) {
		return httpRequest.setConnectTimeout(connectTimeout);
	}

	public int getReadTimeout() {
		return httpRequest.getReadTimeout();
	}

	public HttpRequest setReadTimeout(int readTimeout) {
		return httpRequest.setReadTimeout(readTimeout);
	}

	public HttpHeaders getHeaders() {
		return httpRequest.getHeaders();
	}

	public HttpRequest setHeaders(HttpHeaders headers) {
		return httpRequest.setHeaders(headers);
	}

	public HttpHeaders getResponseHeaders() {
		return httpRequest.getResponseHeaders();
	}

	public HttpRequest setResponseHeaders(HttpHeaders responseHeaders) {
		return httpRequest.setResponseHeaders(responseHeaders);
	}

	public HttpExecuteInterceptor getInterceptor() {
		return httpRequest.getInterceptor();
	}

	public HttpRequest setInterceptor(HttpExecuteInterceptor interceptor) {
		return httpRequest.setInterceptor(interceptor);
	}

	public HttpUnsuccessfulResponseHandler getUnsuccessfulResponseHandler() {
		return httpRequest.getUnsuccessfulResponseHandler();
	}

	public HttpRequest setUnsuccessfulResponseHandler(HttpUnsuccessfulResponseHandler unsuccessfulResponseHandler) {
		return httpRequest.setUnsuccessfulResponseHandler(unsuccessfulResponseHandler);
	}

	public HttpIOExceptionHandler getIOExceptionHandler() {
		return httpRequest.getIOExceptionHandler();
	}

	public HttpRequest setIOExceptionHandler(HttpIOExceptionHandler ioExceptionHandler) {
		return httpRequest.setIOExceptionHandler(ioExceptionHandler);
	}

	public HttpResponseInterceptor getResponseInterceptor() {
		return httpRequest.getResponseInterceptor();
	}

	public HttpRequest setResponseInterceptor(HttpResponseInterceptor responseInterceptor) {
		return httpRequest.setResponseInterceptor(responseInterceptor);
	}

	public int getNumberOfRetries() {
		return httpRequest.getNumberOfRetries();
	}

	public HttpRequest setNumberOfRetries(int numRetries) {
		return httpRequest.setNumberOfRetries(numRetries);
	}

	public HttpRequest setParser(ObjectParser parser) {
		return httpRequest.setParser(parser);
	}

	public final ObjectParser getParser() {
		return httpRequest.getParser();
	}

	public boolean getFollowRedirects() {
		return httpRequest.getFollowRedirects();
	}

	public HttpRequest setFollowRedirects(boolean followRedirects) {
		return httpRequest.setFollowRedirects(followRedirects);
	}

	public boolean getThrowExceptionOnExecuteError() {
		return httpRequest.getThrowExceptionOnExecuteError();
	}

	public HttpRequest setThrowExceptionOnExecuteError(boolean throwExceptionOnExecuteError) {
		return httpRequest.setThrowExceptionOnExecuteError(throwExceptionOnExecuteError);
	}

	public boolean getRetryOnExecuteIOException() {
		return httpRequest.getRetryOnExecuteIOException();
	}

	public HttpRequest setRetryOnExecuteIOException(boolean retryOnExecuteIOException) {
		return httpRequest.setRetryOnExecuteIOException(retryOnExecuteIOException);
	}

	public boolean getSuppressUserAgentSuffix() {
		return httpRequest.getSuppressUserAgentSuffix();
	}

	public HttpRequest setSuppressUserAgentSuffix(boolean suppressUserAgentSuffix) {
		return httpRequest.setSuppressUserAgentSuffix(suppressUserAgentSuffix);
	}

	public HttpResponse execute() throws IOException {
		return httpRequest.execute();
	}

	public Future<HttpResponse> executeAsync(Executor executor) {
		return httpRequest.executeAsync(executor);
	}

	public Future<HttpResponse> executeAsync() {
		return httpRequest.executeAsync();
	}

	public boolean handleRedirect(int statusCode, HttpHeaders responseHeaders) {
		return httpRequest.handleRedirect(statusCode, responseHeaders);
	}

	public Sleeper getSleeper() {
		return httpRequest.getSleeper();
	}

	public HttpRequest setSleeper(Sleeper sleeper) {
		return httpRequest.setSleeper(sleeper);
	}
}
