package org.cloud.storage.cli.executor;

import java.io.IOException;

import http.factories.RequestFactoryFacade;

public interface RunnableCommand {
	void run() throws IOException;

	default RequestFactoryFacade createRequestFactoryFacade() {
		return new RequestFactoryFacade();
	}
}
