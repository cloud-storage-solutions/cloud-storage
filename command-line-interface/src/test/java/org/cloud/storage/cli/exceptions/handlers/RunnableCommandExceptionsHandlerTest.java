package org.cloud.storage.cli.exceptions.handlers;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class RunnableCommandExceptionsHandlerTest {
	private static final String EXCEPTION_MESSAGE = "exceptionMessage";

	@Spy
	private RunnableCommandExceptionsHandler runnableCommandExceptionsHandler;

	@Mock
	private Exception exception;

	@Mock
	private Logger logger;

	@Before
	public void setUp() {
		when(exception.getMessage()).thenReturn(EXCEPTION_MESSAGE);
		doReturn(logger).when(runnableCommandExceptionsHandler).getLogger();
	}

	@Test
	public void testHandle() {
		runnableCommandExceptionsHandler.handle(exception);

		verify(runnableCommandExceptionsHandler).logErrorMessage(EXCEPTION_MESSAGE);
		verify(logger).error(EXCEPTION_MESSAGE);
	}
}
