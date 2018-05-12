package org.cloud.storage.cli.executor;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.cloud.storage.cli.executor.CommandExecutorImpl;
import org.cloud.storage.cli.executor.RunnableCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.rvesse.airline.Cli;

@RunWith(MockitoJUnitRunner.class)
public class CommandExecutorTest {
	private static final String ARGS[] = { "arg1", "arg2", "arg3" };

	private CommandExecutorImpl commandExecutor;

	@Mock
	private Cli<? extends RunnableCommand> cli;

	@Mock
	private RunnableCommand runnableCommand;

	private Optional<RunnableCommand> optionalRunnableCommand;

	@Before
	public void setUp() {
		commandExecutor = Mockito.spy(new CommandExecutorImpl());
		optionalRunnableCommand = Optional.of(runnableCommand);
	}

	@Test
	public void testExecuteAndRunCommand() throws IOException {
		doReturn(optionalRunnableCommand).when(commandExecutor).createRunnableCommandOptional(cli, ARGS);

		commandExecutor.execute(cli, ARGS);

		verify(runnableCommand).run();
	}

	@Test(expected = NoSuchElementException.class)
	public void testExecuteFailToMissingCommand() {
		doThrow(NoSuchElementException.class).when(commandExecutor).createRunnableCommandOptional(cli, ARGS);

		commandExecutor.execute(cli, ARGS);
	}

	@Test
	public void testCreateRunnableCommandOptional() {
		doReturn(runnableCommand).when(cli).parse(ARGS);

		commandExecutor.createRunnableCommandOptional(cli, ARGS);

		verify(cli).parse(ARGS);
	}
}
