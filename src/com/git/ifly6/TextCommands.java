package com.git.ifly6;

public class TextCommands extends Console {

	public static String preoperand = Console.getInputField();

	public static void processInputField() {
		preoperand = Console.getInputField();
		process(preoperand);
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
	public static void process(String preoperand) {

		// Command Parsing
		command(preoperand);
		Console.clearText(3);
		String[] operand = preoperand.split(" ");

		// Command Evaluation
		if ((commText.get(0)).equals(operand[0])) {
			HelpCommands.changeLog();
			log("Changelog Called");
		}
		if ((commText.get(1)).equals(operand[0])) {
			HelpCommands.about();
			log("'About' Processing Trigger Invoked");
		}
		if ((commText.get(2)).equals(operand[0])) {
			HelpCommands.helpList();
			log("Help Processing Trigger Invoked");
		}
		if ((commText.get(3)).equals(operand[0])) {
			Console.clearText(1);
			Console.clearText(2);
			Console.clearText(3);
			log("JTextAreas Cleared");
		}
		if ((commText.get(4)).equals(operand[0])) {
			HelpCommands.acknowledgements();
			log("Acknowledgements Called");
		}
		if ((commText.get(5)).equals(operand[0])) {
			HelpCommands.licence();
			log("EULA Displayed");
		}
		if ((commText.get(6)).equals(operand[0])) {
			FileCommands.export(1);
			log("Invoked Export of outText");
		}
		if ((commText.get(7)).equals(operand[0])) {
			FileCommands.export(2);
			log("Invoked Export of logText");
		}
		if ((commText.get(8)).equals(operand[0])) {
			FileCommands.configManage(3);
			log("Called Configuration Generation thru CLI");
		}
		if ((commText.get(9)).equals(operand[0])) {
			ScriptCommands.readout();
			log("System Information Processing Trigger Called");
		}
		if ((commText.get(10)).equals(operand[0])) {
			ScriptCommands.mindterm();
			log("Mindterm Download Processing Trigger Called");
		}
		if ((commText.get(11)).equals(operand[0])) {
			CommandCommands.terminateChoose();
			log("Process Termination Processing Trigger Called");
		}
		if ((commText.get(12)).equals(operand[0])) {
			System.exit(0);
			log("System.exit(0)");
		}

		// If it does not start with "/", then treat it as a bash command.
		if (!(operand[0].startsWith("/"))) {
			ExecEngine.exec(operand);
		}
	}

}
