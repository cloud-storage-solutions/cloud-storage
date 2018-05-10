package org.cloud.storage.cli.commands;

import org.cloud.storage.cli.execute.CommandRunnable;

import com.github.rvesse.airline.annotations.Command;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand implements CommandRunnable {

	@Override
	public void run() {
		System.out.println("quota");
	}

}