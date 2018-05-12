package org.cloud.storage.cli.exceptions.handlers;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class RunnableCommandExceptionsHandlerTest {
	private static final String EXCEPTION_MESSAGE = "exceptionMessage";

	private RunnableCommandExceptionsHandler runnableCommandExceptionsHandler;

	@Mock
	private Exception exception;

	@Test
	public void test() {
		runnableCommandExceptionsHandler = Mockito.spy(new RunnableCommandExceptionsHandler());

		Mockito.doReturn(EXCEPTION_MESSAGE).when(exception).getMessage();
		runnableCommandExceptionsHandler.handle(exception);
		Mockito.verify(runnableCommandExceptionsHandler).logErrorMessage(EXCEPTION_MESSAGE);
	}
}
