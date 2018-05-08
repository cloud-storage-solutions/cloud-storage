package org.cloud.storage.cli;

import java.util.List;

import com.github.rvesse.airline.annotations.Arguments;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

@Command(name = "upload", description = "Upload a file")
public class UploadCommand implements Runnable {

	@Option(name = { "-f", "--flag" }, description = "An option that requires no values")
	private boolean flag = false;

	@Arguments(description = "Additional arguments")
	private List<String> args;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}