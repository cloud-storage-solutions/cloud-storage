package org.cloud.storage.cli.exceptions.handlers;

import static java.lang.System.lineSeparator;

public interface ExceptionHandler {
	String EXCEPTION_CAUSE = "Cause: ";
	String EXCEPTION_MESSAGE = "Message: ";

	void handle(final Exception exception);

	default String buildErrorMessage(final Exception exception) {
		return new StringBuilder(RunnableCommandExceptionsHandler.class.getName()).append(lineSeparator())
				.append(EXCEPTION_MESSAGE).append(exception.getMessage()).append(lineSeparator())
				.append(EXCEPTION_CAUSE).append(exception.getCause()).toString();
	}
}
