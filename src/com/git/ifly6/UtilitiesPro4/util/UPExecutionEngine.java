/* Copyright (c) 2015 Kevin Wong
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

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
