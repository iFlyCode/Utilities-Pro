package com.git.ifly6.UtilitiesPro3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

/**
 * All commands or methods which are associated with executing a command, or anything that relates to a File which is
 * not directly covered by the File menu GUI commands class, should be here.
 * 
 * @author ifly6
 * @since 3.0_dev01
 */

public class ExecEngine extends Utilities_Pro {

	private static String ArrayToString(String[] arr) {
		StringBuilder builder = new StringBuilder();
		for (String s : arr) {
			builder.append(s + " ");
		}
		return builder.toString();
	}

	/**
	 * Downloads a file. Replaces the place of "curl -o" in all methods. For the purposes of interoperability between
	 * Windows and Mac, we've begun to remove our reliance on Mac-specific OS commands, such as CURL. This is one of
	 * them. Though the differences in the FS will likely be too great to warrant full interoperability, it is a step
	 * forward.
	 * 
	 * @since 3.0_dev09
	 * @param urlFrom
	 *            The URL to download from. This should go before the directory to put the file in.
	 * @param directory
	 *            The directory to download the file to. This should be after the URL.
	 */
	public static void download(String urlFrom, String directory) {
		// Make sure Utilities Pro's main folders exist.
		Utilities_Pro.mkdirs();

		// Logging.
		log("Downloading file from: " + urlFrom);
		log("Downloading file to: " + directory);

		// Downloading.
		try {
			URL website = new URL(urlFrom);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(directory);
			fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			fos.close();
		} catch (FileNotFoundException e) {
			log("Download of File Failed: File Not Found");
		} catch (IOException e) {
			log("Download of File Failed: IO Exception");
		}
	}

	/**
	 * Executes an arbitrary command from any set of parameters, by calling the engine with the input which is defined
	 * here. This should be the only reference form for the execution of arbitrary bash commands when provided a String.
	 * 
	 * @since 3.0_dev05
	 * @param input
	 *            String to be executed. It is moved into String[] form for the execution engine. The input should be
	 *            delimited by spaces.
	 * @see exec(String[] input)
	 */
	public static void exec(String input) {
		log("Beginning Execution of String: " + input);
		final String[] operand = input.split(" ");
		engine(operand);
	}

	/**
	 * Added so an autonomous script can directly execute a hard-coded String[] input. Should not be used otherwise.
	 * 
	 * @since 3.0_dev06
	 * @param input
	 * @see exec(String input)
	 */
	public static void exec(String[] input) {
		log("Beginning Execution of String[]: " + ArrayToString(input));
		engine(input);
	}

	/**
	 * This is the main execution engine, with one String array used as the input parameter. There should be no other
	 * execution engines around, and all should give a String[] into here for execution by the system. Though it is
	 * discouraged to directly reference this, if you want to, go ahead. Furthermore, when doing File I/O, it is
	 * imperative to reference this method. There is no real other way of doing so.
	 * 
	 * @param input
	 *            the String array used as the main execution parameters, getting all the data necessary for the
	 *            execution
	 * @since 3.0_dev05, though its predecessor was implemented in v1.0
	 * @see com.me.ifly6.UtilitiesPro2.methods.CoreMethods
	 */
	private static void engine(final String[] input) {

		// log("Current Directory is: " + Utilities_Pro.currentDir);

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					// Output Stream

					ProcessBuilder builder = new ProcessBuilder(input);
					builder.directory(new File(Utilities_Pro.currentDir));
					process = builder.start();

					log("Execution of input is Beginning");
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
				} catch (IOException e) { // Must distinguish between 'Invalid Commands' and
											// 'Running Faileds'
					out("Invalid Command");
					log("Running Failed or Invalid Command");
				}
			}
		};
		new Thread(runner).start();
	}

	/**
	 * Passes the bash script to bash instead of trying to run it through Java. This allows us to do bash logic without
	 * problems, solving a major problem in our last implementation of scripting.
	 * 
	 * @since 3.1_04
	 * @param script
	 *            - The location of the script we are trying to run.
	 */
	public static void scriptEngine(final String script) {

		// log("Current Directory is: " + Utilities_Pro.currentDir);

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				try {
					// Output Stream

					ProcessBuilder builder = new ProcessBuilder("/bin/bash", script);
					builder.directory(new File(Utilities_Pro.currentDir));
					process = builder.start();

					log("Execution of Script is Beginning");
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
					out("Script Error");
					log("Running Failed and Script Error");
				}
			}
		};
		new Thread(runner).start();
	}
}
