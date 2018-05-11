package org.cloud.storage.cli.commands;

import java.io.IOException;

import org.cloud.storage.cli.execute.RunnableCommand;

import com.github.rvesse.airline.annotations.Command;

import http.factories.RequestFactoryFacade;

@Command(name = "quota", description = "Get the total account quota")
public class QuotaCommand implements RunnableCommand {

	@Override
	public void run() throws IOException {
		new RequestFactoryFacade().createGetRequest("https://localhost:8080/quota");
	}

}