package com.git.ifly6;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * All Command Line Initiated Commands go here, with relevant processing and
 * relevant execution path lines.
 * 
 * @author ifly6
 * @since 3.0_dev01
 */

public class ExecEngine extends Console {

	static String preoperand = Console.getInputField();
	static String[] operand = preoperand.split(" ");

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
		engine(input);
	}

	/**
	 * Processes the Text for Application-specific functions. This should be the
	 * only method called from this class. All others should be called from this
	 * class under certain conditions. All internal commands MUST begin with
	 * "/", just like in Minecraft.
	 * 
	 * @since 1.2
	 * @see com.me.ifly6.TextProc
	 */
	public static void process() {
		preoperand = Console.getInputField();

		// Command Parsing
		append(computername + "~ $ " + preoperand);
		Console.clearText(3);
		operand = preoperand.split(" ");

		// Command Evaluation
		if (commText[0].equals(operand[0])) {
			HelpCommands.changeLog();
			log("Changelog Called");
		}
		if (commText[1].equals(operand[0])) {
			HelpCommands.about();
			log("'About' Processing Trigger Invoked");
		}
		if (commText[2].equals(operand[0])) {
			HelpCommands.helpList();
			log("Help Processing Trigger Invoked");
		}
		if (commText[3].equals(operand[0])) {
			Console.clearText(1);
			Console.clearText(2);
			Console.clearText(3);
			log("JTextAreas Cleared");
		}
		if (commText[4].equals(operand[0])) {
			HelpCommands.acknowledgements();
			log("Acknowledgements Called");
		}
		/*
		 * if (commText[5].equals(operand[0])) { int tmp = 11; tmp =
		 * java.lang.Integer.parseInt(operand[2]); Font font = new
		 * Font(operand[1], 0, tmp); display.setFont(font); log("Font changed");
		 * }
		 */
		if (commText[6].equals(operand[0])) {
			HelpCommands.licence();
			log("EULA Displayed");
		}
		if (commText[7].equals(operand[0])) {
			FileCommands.export(1);
			log("Invoked Export of outText");
		}
		if (commText[8].equals(operand[0])) {
			FileCommands.export(2);
			log("Invoked Export of logText");
		}
		if (commText[9].equals(operand[0])) {
			FileCommands.configManage(2);
			log("Deletion Processing Trigger Called");
		}
		if (commText[10].equals(operand[0])) {
			ScriptCommands.readout();
			log("System Information Processing Trigger Called");
		}
		if (commText[11].equals(operand[0])) {
			ScriptCommands.mindterm();
			log("Mindterm Download Processing Trigger Called");
		}
		if (commText[12].equals(operand[0])) {
			CommandCommands.terminateChoose();
			log("Process Termination Processing Trigger Called");
		}
		/*
		 * if (commText[13].equals(operand[0])) {
		 * 
		 * } if (commText[14].equals(operand[0])) {
		 * 
		 * }
		 */
		if (commText[15].equals(operand[0])) {
			System.exit(0);
			log("System.exit(0)");
		}

		// If it does not start with "/", then treat it as a bash command.
		if (!(operand[0].startsWith("/"))) {
			exec();
		}
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
