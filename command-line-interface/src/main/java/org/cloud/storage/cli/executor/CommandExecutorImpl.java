package org.cloud.storage.cli.executor;

import java.io.IOException;
import java.util.Optional;

import org.cloud.storage.cli.exceptions.handlers.RunnableCommandExceptionsHandler;

import com.github.rvesse.airline.Cli;

public class CommandExecutorImpl implements CommandExecutor {
	private RunnableCommandExceptionsHandler runnableCommandExceptionsHandler;

	@Override
	public <R extends AbstractRunnableCommand> void execute(final Cli<R> cli, final String... args) {
		Optional<? extends AbstractRunnableCommand> runnableCommandOptional = createRunnableCommandOptional(cli, args);
		AbstractRunnableCommand runnableCommand = runnableCommandOptional.get();
		try {
			runnableCommand.run();
		} catch (IOException ioException) {
			runnableCommandExceptionsHandler.handle(ioException);
		}
	}

	protected <R extends AbstractRunnableCommand> Optional<AbstractRunnableCommand> createRunnableCommandOptional(final Cli<R> cli,
			final String... args) {
		return Optional.of(cli.parse(args));
	}
}
