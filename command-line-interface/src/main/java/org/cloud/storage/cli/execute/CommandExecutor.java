package org.cloud.storage.cli.execute;

import com.github.rvesse.airline.Cli;

public interface CommandExecutor {
	<R extends CommandRunnable> void execute(Cli<R> cli, String... args);
}
