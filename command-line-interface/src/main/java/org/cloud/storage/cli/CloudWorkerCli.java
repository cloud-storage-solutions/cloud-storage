package org.cloud.storage.cli;

import com.github.rvesse.airline.annotations.Cli;

@Cli(name = "cloud-worker-cli", description = "desc", commands = { UploadCommand.class, QuotaCommand.class })
public class CloudWorkerCli {
	public static void main(String... args) {
		com.github.rvesse.airline.Cli<Runnable> cli = new com.github.rvesse.airline.Cli<>(CloudWorkerCli.class);
		Runnable command = cli.parse(args);
		command.run();
	}
}
