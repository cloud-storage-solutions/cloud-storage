package org.cloud.storage.cli.execute;

import java.util.Optional;

import com.github.rvesse.airline.Cli;

public class CommandExecutorImpl implements CommandExecutor {

	@Override
	public <R extends RunnableCommand> void execute(final Cli<R> cli, final String... args) {
		Optional<? extends RunnableCommand> runnableCommandOptional = createRunnableCommandOptional(cli, args);
		RunnableCommand runnableCommand = runnableCommandOptional.get();
		runnableCommand.run();
	}

	protected <R extends RunnableCommand> Optional<RunnableCommand> createRunnableCommandOptional(final Cli<R> cli,
			final String... args) {
		return Optional.of(cli.parse(args));
	}
}
