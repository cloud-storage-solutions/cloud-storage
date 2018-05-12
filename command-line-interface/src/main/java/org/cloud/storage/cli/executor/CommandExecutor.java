package org.cloud.storage.cli.executor;

import com.github.rvesse.airline.Cli;

public interface CommandExecutor {
	<R extends RunnableCommand> void execute(final Cli<R> cli, final String... args);
}
