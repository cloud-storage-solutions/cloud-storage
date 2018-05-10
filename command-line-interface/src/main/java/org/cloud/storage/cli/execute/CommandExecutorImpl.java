package org.cloud.storage.cli.execute;

import com.github.rvesse.airline.Cli;

public class CommandExecutorImpl implements CommandExecutor {

	@Override
	public <R extends CommandRunnable> void execute(final Cli<R> cli, final String... args) {
		CommandRunnable commandRunnable = cli.parse(args);
		commandRunnable.run();
	}

}
