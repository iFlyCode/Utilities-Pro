package com.git.ifly6.UtilitiesPro3;

public class TextCommands extends Utilities_Pro {

	public static String preoperand = Utilities_Pro.getInputField();

	public static void processInputField() {
		preoperand = Utilities_Pro.getInputField();
		process(preoperand);
	}

	/**
	 * Processes the Text for Application-specific functions. This should be the
	 * only method called from this class. All others should be called from this
	 * class under certain conditions. All internal commands MUST begin with
	 * "/", just like in Minecraft.
	 * 
	 * @since 1.2
	 * @see com.me.ifly6.UtilitiesPro2.TextProc
	 */
	public static void process(String preoperand) {

		// Command Parsing
		command(preoperand);
		Utilities_Pro.clearText(3);
		String[] operand = preoperand.split(" ");

		// Command Evaluation
		if ((commText.get(0)).equals(operand[0])) {
			HelpCommands.changeLog();
			log("Changelog Called");
		} else if ((commText.get(1)).equals(operand[0])) {
			HelpCommands.about();
			log("'About' Processing Trigger Invoked");
		} else if ((commText.get(2)).equals(operand[0])) {
			HelpCommands.helpList();
			log("Help Processing Trigger Invoked");
		} else if ((commText.get(3)).equals(operand[0])) {
			Utilities_Pro.clearText(1);
			Utilities_Pro.clearText(2);
			Utilities_Pro.clearText(3);
			log("JTextAreas Cleared");
		} else if ((commText.get(4)).equals(operand[0])) {
			HelpCommands.acknowledgements();
			log("Acknowledgements Called");
		} else if ((commText.get(5)).equals(operand[0])) {
			HelpCommands.licence();
			log("EULA Displayed");
		} else if ((commText.get(6)).equals(operand[0])) {
			FileCommands.export(1);
			log("Invoked Export of outText");
		} else if ((commText.get(7)).equals(operand[0])) {
			FileCommands.export(2);
			log("Invoked Export of logText");
		} else if ((commText.get(8)).equals(operand[0])) {
			FileCommands.configManage(3);
			log("Called Configuration Generation thru CLI");
		} else if ((commText.get(9)).equals(operand[0])) {
			ScriptCommands.readout();
			log("System Information Processing Trigger Called");
		} else if ((commText.get(10)).equals(operand[0])) {
			ScriptCommands.mindterm();
			log("Mindterm Download Processing Trigger Called");
		} else if ((commText.get(11)).equals(operand[0])) {
			CommandCommands.terminateChoose();
			log("Process Termination Processing Trigger Called");
		} else if ((commText.get(12)).equals(operand[0])) {
			System.exit(0);
			log("System.exit(0)");
		} else if (operand[0].equals("cd")) {
			cd(operand);
		}

		// Finally at the end of the cascade of 'if' statements,
		// if operand does not start with "/", then treat it as a bash command.
		else {
			ExecEngine.exec(operand);
		}
	}

	/**
	 * The CD Subsystem. Much waiting was done for this. One epiphany later, it
	 * was solved.
	 * 
	 * @since 3.0_dev09.03
	 * @param operand
	 */
	public static void cd(String[] operand) {
		if (operand[1].startsWith("/")) {
			Utilities_Pro.currentDir = operand[1];
		} else if (operand[1].startsWith("~")) {
			String newDir = operand[1].replaceAll("~",
					System.getProperty("user.home"));
			Utilities_Pro.currentDir = newDir;
		} else {
			Utilities_Pro.currentDir = Utilities_Pro.currentDir + "/" + operand[1];
		}
	}
}
