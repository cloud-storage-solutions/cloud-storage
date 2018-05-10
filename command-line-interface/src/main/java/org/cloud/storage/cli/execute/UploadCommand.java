package org.cloud.storage.cli.execute;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

@Command(name = "upload", description = "Upload a file")
public class UploadCommand implements CommandRunnable {

	@Option(name = { "-f", "--flag" }, description = "An option that requires no values")
	private boolean flag = false;

	@Override
	public void run() {
		System.out.println("hello world");

	}

}