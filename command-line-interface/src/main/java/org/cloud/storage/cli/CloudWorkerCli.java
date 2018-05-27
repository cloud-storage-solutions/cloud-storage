package org.cloud.storage.cli;

import org.cloud.storage.cli.commands.QuotaCommand;
import org.cloud.storage.cli.executor.CommandExecutor;
import org.cloud.storage.cli.executor.CommandExecutorImpl;
import org.cloud.storage.cli.executor.RunnableCommand;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.github.rvesse.airline.annotations.Cli;
import com.github.rvesse.airline.annotations.Group;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "org.cloud.storage.cli.factories", "org.cloud.storage.cli.commands.configuration" })
@Cli(name = "cloud-worker-cli", description = "Cloud Worker CLI", groups = {
		@Group(name = "cloud", commands = { QuotaCommand.class }) })
public class CloudWorkerCli {
	public static void main(String... args) {
		com.github.rvesse.airline.Cli<RunnableCommand> cli = new com.github.rvesse.airline.Cli<>(CloudWorkerCli.class);
		String a[] = { "cloud", "quota" };
		CommandExecutor commandExecutor = new CommandExecutorImpl();
		commandExecutor.execute(cli, a);
	}
}
