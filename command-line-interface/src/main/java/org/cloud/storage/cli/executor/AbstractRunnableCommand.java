package org.cloud.storage.cli.executor;

import java.io.IOException;

import org.cloud.storage.cli.commands.configuration.CommandConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractRunnableCommand {
	@Autowired
	protected CommandConfiguration commandConfiguration;

	public abstract void run() throws IOException;
}
