package com.git.ifly6;

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
 * All commands or methods which are associated with executing a command, or
 * anything that relates to a File which is not directly covered by the File
 * menu GUI commands class, should be here.
 * 
 * @author ifly6
 * @since 3.0_dev01
 */

public class ExecEngine extends Console {

	/**
	 * Downloads a file. Replaces the place of "curl -o" in programme. For the
	 * purposes of interoperability between Windows and Mac, we've begun to
	 * remove our reliance on Mac-specific OS commands, such as CURL. This is
	 * one of them. Though the differences in the FS will likely be too great to
	 * warrant full interoperability, this is a step forward.
	 * 
	 * @since 3.0_dev09
	 * @param urlFrom
	 *            The URL to download from. This should go before the directory
	 *            to put the file in.
	 * @param directory
	 *            The directory to download the file to. This should be after
	 *            the URL.
	 */
	public static void download(String urlFrom, String directory) {
		// Make sure Utilities Pro's main folders exist.
		Console.mkdir();

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
	 * Sets the input String array as the pre-operand/contents of the
	 * inputField, and calls the engine.
	 * 
	 * @since 3.0_dev05
	 * @param input
	 *            a string to be run by the execution engine.
	 * @see com.me.ifly6.TextProc
	 * @see exec(String input)
	 * @see exec(String[] input)
	 */
	public static void exec() {
		log("Beginning Execution of : " + Console.getInputField());
		final String[] input = Console.getInputField().split(" ");
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
	 * @see exec()
	 * @see exec(String[] input)
	 */
	public static void exec(String input) {
		log("Beginning Execution of Arbitrary: " + input);
		final String[] operand = input.split(" ");
		engine(operand);
	}

	/**
	 * Added to ensure continuity with the other "exec" methods.
	 * 
	 * @since 3.0_dev06
	 * @param input
	 * @see exec()
	 * @see exec(String input)
	 */
	public static void exec(String[] input) {
		log("Beginning Execution of: " + input.toString());
		engine(input);
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
	 * @since 3.0_dev05, though its predecessor was implemented in v1.0
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
