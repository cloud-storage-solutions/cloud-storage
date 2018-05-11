package org.cloud.storage.cli.exceptions.handler;

import static java.lang.System.lineSeparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunnableCommandExceptionsHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(RunnableCommandExceptionsHandler.class);

	private static final String CAUSE = "Cause: ";
	private static final String MESSAGE = "Message: ";

	public void handle(final Exception exception) {
		LOGGER.error(buildErrorMessage(exception));
	}

	private String buildErrorMessage(final Exception exception) {
		return new StringBuilder(RunnableCommandExceptionsHandler.class.getName()).append(lineSeparator())
				.append(MESSAGE).append(exception.getMessage()).append(lineSeparator()).append(CAUSE)
				.append(exception.getCause()).toString();
	}
}
