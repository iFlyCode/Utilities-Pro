/* Copyright (c) 2015 ifly6
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

package ifly6.UtilitiesPro3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class TextCommands extends Utilities_Pro {

	/**
	 * Logs with a change to reflect a different section of the log.
	 *
	 * @param input
	 *            - String to be logged.
	 * @param type
	 *            - If true, it logs with 2 equals signs before it, saying it is a subset of the log above. If false, it
	 *            is logged normally.
	 * @since 3.3_dev05
	 */
	static void log(String input, int type) {
		if (type == 0) {
			log(input);
		} else if (type == 1) {
			log("== " + input);
		} else if (type == 2) { // other types of logs should be added here. create numbers as necessary.

		} else {
			log("Section is attempting to TextCommands.log with an invalid integer specification.");
		}
	}

	protected static void processInputField() {
		String preoperand = getInputField().getText();
		process(preoperand);
	}

	/**
	 * Processes the Text for Application-specific functions. This should be the only method called from this class. All
	 * others should be called from this class under certain conditions. All internal commands MUST begin with "/", just
	 * like in Minecraft. Updated in 3.2 to accept escape characters for space (that being '\ ').
	 *
	 * @since 1.2
	 * @see com.me.ifly6.UtilitiesPro2.TextProc
	 */
	static void process(String preoperand) {

		log("Processing operand: " + preoperand);

		// Get and Save Command
		command(preoperand);
		addToHistory(preoperand);
		Utilities_Pro.clearText(3);

		// Split this using this Regular Expression
		String[] operand = preoperand.split("(?<!\\\\)\\s+");

		// Remove all remaining backslashes, as Java doesn't like them.
		for (int x = 0; x < operand.length; x++) {
			if (operand[x].contains("\\")) {
				operand[x] = operand[x].replace("\\", "");
			}
		}

		// Command Evaluation
		if (commText.get(0).equals(operand[0])) {
			HelpCommands.changeLog();
			log("Changelog Called");
		} else if (commText.get(1).equals(operand[0])) {
			HelpCommands.about();
			log("'About' Processing Trigger Invoked");
		} else if (commText.get(2).equals(operand[0]) || "?".equals(operand[0]) || "help".equals(operand[0])) {
			HelpCommands.helpList();
			log("Help Processing Trigger Invoked");
		} else if (commText.get(3).equals(operand[0])) {
			Utilities_Pro.clearText(1);
			Utilities_Pro.clearText(2);
			Utilities_Pro.clearText(3);
			log("JTextAreas Cleared");
		} else if (commText.get(4).equals(operand[0])) {
			HelpCommands.licence();
			log("EULA Displayed");
		} else if (commText.get(5).equals(operand[0])) {
			FileCommands.export(1);
			log("Invoked Export of outText");
		} else if (commText.get(6).equals(operand[0])) {
			FileCommands.export(2);
			log("Invoked Export of logText");
		} else if (commText.get(7).equals(operand[0])) {
			// Call textConfig and pass information to it.
			textConfig(operand);
		} else if (commText.get(8).equals(operand[0])) {
			ScriptCommands.mindterm();
			log("Mindterm Download Processing Trigger Called");
		} else if (commText.get(9).equals(operand[0])) {
			log("ScriptExecution Trigger Called");
			ExecEngine.scriptEngine(operand[1]);
		} else if (commText.get(10).equals(operand[0])) {
			log("Monitor Trigger Called");
			// TODO Create Monitor
		} else if (commText.get(11).equals(operand[0])) {
			log("Plugin Loader Called");
			pluginLogic(operand);
		} else if ((commText.get(12)).equals(operand[0])) {
			CommandCommands.terminateChoose();
			log("Process Termination Processing Trigger Called");
		} else if (commText.get(13).equals(operand[0])) {
			System.exit(0);
		}

		/* If it is not a '/' type internal command, see if it is calling for the implementation of 'cd' or anything
		 * else like that. */
		else if (operand[0].equals("cd")) {
			cd(operand); // Engage change directory engine
		} else if (operand[0].equals("clear")) {
			Utilities_Pro.clearText(1); // Clear the output JTextArea
		} else if (operand[0].equals("path")) {
			path(); // Show Path
		} else if (operand[0].equals("top") || operand[0].equals("htop")) {
			out("The command 'top' is not supported because of technical limitations of Java. \n Please use the string "
					+ "'ps -arxo cpu,pid,args' instead.");
		}

		/* If it is not a internal command, treat it as it it were a bash command. */
		else {
			ExecEngine.exec(operand);
		}

	}

	/**
	 * The CD entire Subsystem. Much waiting was done for this. One epiphany later, it was solved. Updated in 3.1 to
	 * include way of dealing with spaces in filenames. However, when the entire space system was overhauled in 3.2, it
	 * became unnecessary due to the escape char for space ('\ '). Since 3.1_03, it also checks whether the DIR you are
	 * trying to go to actually exists.
	 *
	 * @since 3.0_dev09.03
	 * @param operand
	 *            - The command which was put in. This command can begin with anything, but when called, should only
	 *            being with 'cd'.
	 */
	public static void cd(String[] operand) {
		String nonCanonical = "";
		String dirExists = "The directory you are looking for does not exist, is not a directory, or there has been an error.";
		String dirPerms = "The directory have selected is restricted";

		try {
			// Deal with Files that start with '/'
			if (operand[1].startsWith("/")) {
				if (new File(operand[1]).isDirectory() && new File(operand[1]).canRead()) {
					nonCanonical = operand[1];
					try {
						currentDir = new File(nonCanonical).getCanonicalPath();
					} catch (IOException e) {
						out("Changing Directory somehow failed. Report this error to GitHub.");
					}
				} else if (!(new File(operand[1]).canRead())) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
			}

			// Deal with things that start with '~'
			else if (operand[1].startsWith("~")) {
				String newDir = operand[1].replaceAll("~", System.getProperty("user.home"));
				if (new File(newDir).isDirectory() && new File(newDir).canRead()) {
					nonCanonical = newDir;
					try {
						currentDir = new File(nonCanonical).getCanonicalPath();
					} catch (IOException e) {
						out("Changing Directory somehow failed. Report this error to GitHub.");
					}
				} else if (!(new File(newDir).canRead())) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
			}

			// Deal with Everything Else
			else {
				if (new File(Utilities_Pro.currentDir + "/" + operand[1]).isDirectory()
						&& new File(Utilities_Pro.currentDir + "/" + operand[1]).canRead()) {
					nonCanonical = Utilities_Pro.currentDir + "/" + operand[1];
					try {
						currentDir = new File(nonCanonical).getCanonicalPath();
					} catch (IOException e) {
						out("Changing Directory somehow failed. Report this error to GitHub.");
					}
				} else if (!(new File(Utilities_Pro.currentDir + "/" + operand[1]).canRead())) {
					out(dirPerms);
				} else {
					out(dirExists);
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			out("Add argument to change directory command.");
		}

	}

	/**
	 * Display the current Path.
	 *
	 * @since 3.1_02_dev01
	 */
	public static void path() {
		out(currentDir);
	}

	/**
	 * Uses advanced recognition technology to auto-complete what is being looked for. Since 3.3_dev01, it also includes
	 * directory confirmation, and support for space-escape auto-formatting. Since 3.3_dev02, it also includes infinite
	 * file list comparation.
	 *
	 * @since 3.3
	 */
	static String tabComplete() {
		command("> Tab Auto-Complete");

		String preoperand = getInputField().getText();

		String[] operand = preoperand.split("(?<!\\\\)\\s+");
		String[] dirLayer = operand[operand.length - 1].split("/");
		boolean absPath = operand[operand.length - 1].startsWith("/");
		boolean usrPath = operand[operand.length - 1].startsWith("~");
		String lookDir = currentDir;

		log("Raw: " + preoperand);
		log("Search Term: " + dirLayer[dirLayer.length - 1]);

		if ((dirLayer.length - 1) >= 1) {
			StringBuilder builder = new StringBuilder();

			if (absPath) { // Is it an absolute Path?
				for (int x = 0; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			} else if (usrPath) { // Is it a ~ Path?
				builder.append(System.getProperty("user.home"));
				for (int x = 1; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			} else {
				builder.append(currentDir);
				for (int x = 0; x < (dirLayer.length - 1); x++) {
					builder.append("/" + dirLayer[x]);
				}
			}
			lookDir = builder.toString();
			log("LOG: Looking At " + lookDir);
		}

		String[] fileList = new File(lookDir).list();
		ArrayList<String> narrowList = new ArrayList<String>();

		for (String element : fileList) {
			if (operand[0].equals("cd")) { // With Directory Confirmation!
				if (element.startsWith(dirLayer[dirLayer.length - 1])
						&& new File(lookDir + "/" + element).isDirectory()) {
					narrowList.add(element);
				}
			} else { // To get completed file names!
				if (element.startsWith(dirLayer[dirLayer.length - 1])) {
					narrowList.add(element);
				}
			}
		}

		if (narrowList.isEmpty()) { // No Matches
			out("No Directory Match for keyword: " + dirLayer[dirLayer.length - 1]);
		} else if (narrowList.size() == 1) { // One Match

			StringBuilder builder = new StringBuilder();
			String selected = narrowList.get(0);
			String reinput = narrowList.get(0);

			// Deal with possible spaces.
			if (selected.contains(" ")) {
				selected = selected.replace(" ", "\\ ");
			}

			// Deal with possible Path Issues
			if (absPath || usrPath) {
				selected = lookDir + "/" + selected;
			}

			operand[operand.length - 1] = selected; // Load

			// Recombine the String
			for (String element : operand) {
				builder.append(element + " ");
			}

			reinput = builder.toString().trim();
			return reinput;

		} else if (narrowList.size() > 1) { // More than 1 Match
			out("There is more than one option available. Please continue typing.");
			for (String element : narrowList) {
				out(" * " + element);
			}

			return getInputField().getText();
		}

		// If none of these Returns are triggered, return what was already typed in.
		return getInputField().getText();
	}

	/**
	 * A private method for processing the plugin command and its arguments
	 *
	 * @since 3.3_dev05
	 * @param operand
	 *            - a String[] containing all the pertinent operands for the plugin command.
	 */
	private static void pluginLogic(String[] operand) {
		// Make sure the initial operand is correct.
		if (!(operand[0].equals("/plugin")) || operand[1].equals(null)) {
			return;
		} else {
			if (operand[1].equals("list")) {
				// List all plugins in the folder.
				out("== Plugins ==");
				try {
					for (File element : new File(UtilitiesPro_DIR + "/plugins/").listFiles()) {
						// Only list files with and ending of '.class'
						if (element.toString().endsWith(".class")) {
							out(" * " + element.getName());
						}
					}
				} catch (NullPointerException questionExistence) {
					out("Either the plugin directory does not exist, or there are no plugins.");
					log(questionExistence.toString(), 1);
				}
			} else if (operand[1].equals("help")) {
				// Gives some helpful help information.
				out("== Plugin Help ==");
				out(" Simply find and load a plugin from the list given by '/plugin list'");
				out(" then, execute any public method contained within that plugin using:");
				out(" '/plugin [pluginName] [methodName] [arguments separated by spaces]'");
			} else {
				/* Actually execute the plugin. Due to logic, once cannot have a plugin called 'list' or 'help'.
				 * Furthermore, this operates by using the apiImplementation earlier to execute a method with the
				 * arguments passed to it. */
				String pluginName = operand[1];
				apiImplementation plugin = new apiImplementation(
						new File(UtilitiesPro_DIR + "/plugins/" + pluginName + ".class"));
				if (operand.length < 3) {
					out("Specify a methodName to pass to the plugin.");
				} else {
					ArrayList<String> args = (ArrayList<String>) Arrays.asList(operand);
					args.remove(0);
					args.remove(0);
					plugin.methodInvoke(operand[2], args.toArray());
				}
			}
		}
	}

	/**
	 * A private method for processing the operand command and its arguments.
	 *
	 * @since 3.3_dev02
	 * @param operand
	 *            - String[] containing all the pertinent information.
	 */
	private static void textConfig(String[] operand) {
		log("Called Configuration Handler through CLI");
		try {
			if (operand[1].equals("generate")) {
				System.out.println("Generation Triggered");
				FileCommands.configGen();
			} else if (operand[1].equals("change")) {
				log("Attempt to Change Configuration Logged.");
				try {
					if (operand[2].equals("Look&Feel")) {
						try {
							FileCommands.configHandler(operand[2], operand[3]);
						} catch (NullPointerException NPE) {
							out("Input a value to change the value to.");
							log("Lack of 4th Operand for TextConfig/Change triggered NPE");
						}
					} else if (operand[2].equals("QuitFunction")) {
						try {
							FileCommands.configHandler(operand[2], operand[3]);
						} catch (NullPointerException NPE) {
							out("Input a value to change the value to.");
							log("Lack of 4th Operand for TextConfig/Change triggered NPE");
						}
					}
				} catch (NullPointerException NPE) {
					out("Input a valid value to change. They are 'Look&Feel' and 'QuitFunction'");
					log("Lack of 3rd Operand for TextConfig/Change triggered NPE");
				}
			} else {
				out("Input a valid second operand for '/config'. It may be 'generate' or 'change [value] [key]'");
			}
		} catch (NullPointerException NPE) {
			out("Place an operand after '/config'. It may be 'generate' or 'change [value] [key]'");
			log("Lack of Operand for TextConfig triggered NPE");
		}
	}

	public static void quitHandler() {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream(Utilities_Pro.UtilitiesPro_DIR + "/config.properties");
		try {
			prop.load(stream);

			// If it matches any of these keywords, do it.
			if (prop.getProperty("QuitFunction").equals("purgeMemory")) {
				ScriptCommands.purge();
				System.exit(0);
			} else if (prop.getProperty("QuitFunction").equals("purgeConfiguration")) {
				ScriptCommands.purge();
				FileCommands.deleteConfig(false); // Deletes Configuration
				System.exit(0);
			} else if (prop.getProperty("QuitFunction").equals("purgeFolder")) {
				ScriptCommands.purge();
				FileCommands.deleteConfig(true); // Deletes Utilities Pro DIR
				System.exit(0);
			} else if (prop.getProperty("QuitFunction").equals("persist")) {
				System.exit(0);
			} else {
				System.exit(0);
			}

		} catch (IOException e1) {
			System.exit(0);
		} catch (NullPointerException e1) {
			System.exit(0);
		}
	}
}
