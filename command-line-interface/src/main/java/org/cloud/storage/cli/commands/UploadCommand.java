package org.cloud.storage.cli.commands;

import java.io.IOException;

import org.cloud.storage.cli.executor.AbstractRunnableCommand;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

import http.factories.RequestFactoryFacade;

@Command(name = "upload", description = "Upload a file")
public class UploadCommand extends AbstractRunnableCommand {

	@Option(name = { "-f", "--flag" }, description = "An option that requires no values")
	private boolean flag = false;

	@Override
	public void run() throws IOException {
		new RequestFactoryFacade().createGetRequest("https://localhost:8080/upload......");
	}

}