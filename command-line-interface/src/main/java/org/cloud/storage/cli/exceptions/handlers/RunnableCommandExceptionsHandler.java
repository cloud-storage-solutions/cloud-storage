package org.cloud.storage.cli.exceptions.handlers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RunnableCommandExceptionsHandler implements ExceptionHandler {
	@Override
	public void handle(final Exception exception) {
		logErrorMessage(exception.getMessage());
	}

	protected void logErrorMessage(final String errorMessage) {
		log.error(errorMessage);
	}
}
