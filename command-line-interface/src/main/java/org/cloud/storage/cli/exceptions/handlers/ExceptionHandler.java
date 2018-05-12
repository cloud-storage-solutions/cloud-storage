package org.cloud.storage.cli.exceptions.handlers;

public interface ExceptionHandler {
	void <T extends Throwable> handle(final T throwable);
}
