package com.git.ifly6;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * All Command Line initiated Commands go here
 * 
 * @author ifly6
 * @since 3.0
 */

public class TextCommands extends Console {

	static String preoperand = Console.getInputField();
	static final String[] operand = preoperand.split(" ");

	/**
	 * Execution Processes, the main Command Line execution engine.
	 * 
	 * @since 1.0
	 * @throws IOException
	 */
	public static void exec() {
		log("Beginning Execution of : " + preoperand);
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					// Output Stream
					ProcessBuilder builder = new ProcessBuilder(operand);
					process = builder.start();
					log("Execution of Operand Beginning.");
					InputStream stdout = process.getInputStream();
					InputStreamReader inRead = new InputStreamReader(stdout);
					Scanner scan = new Scanner(inRead);
					while (scan.hasNextLine()) {
						out(scan.nextLine());
					}

					// Error Stream
					InputStream stderr = process.getErrorStream();
					InputStreamReader isr1 = new InputStreamReader(stderr);
					Scanner scan1 = new Scanner(isr1);
					while (scan1.hasNextLine()) {
						out(scan1.nextLine());
					}
				} catch (IOException e) {
					log("Running Failed");
				}
			}
		};
		new Thread(runner).start();
	}

	/**
	 * Processes the Text for Application-specific functions.
	 * 
	 * @since 1.2
	 */
	public static void process() {

	}
}
