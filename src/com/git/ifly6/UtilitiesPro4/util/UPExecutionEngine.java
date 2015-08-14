package com.git.ifly6.UtilitiesPro4.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UPExecutionEngine {

	private UPLogger util;
	private Process process;
	private ProcessBuilder builder;

	public UPExecutionEngine(UPLogger logger) {
		util = logger;
	}

	public void setDir(File currentDir) {
		builder.directory(currentDir);
	}

	// Command Execution
	public void execute(String[] commands) throws IOException {
		builder = new ProcessBuilder(commands);
		process = builder.start();

		// Error Stream
		Runnable errorFetch = new Runnable() {
			@Override public void run() {
				InputStream errStream = process.getErrorStream();
				InputStreamReader errRead = new InputStreamReader(errStream);
				Scanner scan = new Scanner(errRead);
				while (scan.hasNextLine()) {
					util.out(scan.nextLine());
				}
				scan.close();
			}
		};
		util.log("Processing Error Handler Created");
		errorFetch.run();

		util.log("Execution of input is Beginning");
		InputStream outStream = process.getInputStream();
		InputStreamReader outRead = new InputStreamReader(outStream);
		Scanner scan = new Scanner(outRead);
		while (scan.hasNextLine()) {
			util.out(scan.nextLine());
		}
		scan.close();
	}

	// Bash Script Execution
	public void execute(File file) {

	}

}
