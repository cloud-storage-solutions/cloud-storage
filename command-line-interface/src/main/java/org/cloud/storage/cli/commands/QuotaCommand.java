package org.cloud.storage.cli.commands;

import org.cloud.storage.cli.execute.RunnableCommand;

import com.github.rvesse.airline.annotations.Command;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand implements RunnableCommand {

	@Override
	public void run() {
		System.out.println("quota");
	}

}