package com.git.ifly6;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * All Command Line Initiated Commands go here, with relevant processing and
 * relevant execution pathlines.
 * 
 * @author ifly6
 * @since 3.0_dev01
 */

public class TextCommands extends Console {

	static String preoperand = Console.getInputField();
	static final String[] operand = preoperand.split(" ");

	/**
	 * Sets the input String array as the preoperand/contents of the inputField,
	 * and calls the engine.
	 * 
	 * @since 3.0_dev05
	 * @param input
	 *            a string to be run by the execution engine.
	 * @see com.me.ifly6.TextProc
	 */
	public static void exec() {
		log("Beginning Execution of : " + preoperand);
		final String[] input = operand;
		engine(input);
	}

	/**
	 * Executes an arbitrary command from any set of parameters, by calling the
	 * engine with the input which is defined here. This should be the only
	 * reference form for the execution of arbitrary bash commands.
	 * 
	 * @since 3.0_dev05
	 * @param input
	 *            String to be executed. It is moved into String[] form for the
	 *            execution engine. The input should be delimited by spaces.
	 */
	public static void exec(String input) {
		log("Beginning Execution of Arbitrary: " + input);
		final String[] operand = input.split(" ");
		engine(operand);
	}

	/**
	 * Processes the Text for Application-specific functions. This should be the
	 * only method called from this class. All others should be called from this
	 * class under certain conditions.
	 * 
	 * @since 1.2
	 * @see com.me.ifly6.TextProc
	 */
	public static void process() {

	}

	/**
	 * This is the main execution engine, with one String array used as the
	 * input parameter. There should be no other execution engines around, and
	 * all should give a String[] into here for execution by the system. Though
	 * it is discouraged to directly reference this, if you want to, go ahead.
	 * Furthermore, when doing File I/O, it is imperative to reference this
	 * method. There is no real other way of doing so.
	 * 
	 * @param input
	 *            the String array used as the main execution parameters,
	 *            getting all the data necessary for the execution
	 * @since 3.0_dev05, though it's predecessor was implemented in v1.0
	 * @see com.me.ifly6.methods.CoreMethods
	 */
	public static void engine(final String[] input) {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					// Output Stream
					ProcessBuilder builder = new ProcessBuilder(input);
					process = builder.start();
					log("Execution of String Input Beginning");
					InputStream outStream = process.getInputStream();
					InputStreamReader outRead = new InputStreamReader(outStream);
					Scanner scan = new Scanner(outRead);
					while (scan.hasNextLine()) {
						out(scan.nextLine());
					}

					// Error Stream
					InputStream errStream = process.getErrorStream();
					InputStreamReader errRead = new InputStreamReader(errStream);
					scan = new Scanner(errRead);
					while (scan.hasNextLine()) {
						out(scan.nextLine());
					}
				} catch (IOException e) {
					log("Running Failed");
				}
			}
		};
		new Thread(runner).start();
	}
}
