package org.cloud.storage.cli.executor;

import com.github.rvesse.airline.Cli;

public interface CommandExecutor {
	<R extends AbstractRunnableCommand> void execute(final Cli<R> cli, final String... args);
}
