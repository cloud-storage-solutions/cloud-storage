package org.cloud.storage.cli.exceptions.handlers;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RunnableCommandExceptionsHandler implements ExceptionHandler {
	@Override
	public void handle(Exception exception) {
		logErrorMessage(exception.getMessage());
	}

	protected void logErrorMessage(final String errorMessage) {
		getLogger().error(errorMessage);
	}

	protected Logger getLogger() {
		return log;
	}

}
