package com.me.ifly6;

import java.awt.Font;
import java.io.IOException;

public class TextProc extends ConsoleIf {
	// Name: Text Commands (Executes Necessary Text Data from Console/anything else)

	private static final long serialVersionUID = 1L;
	public static Runtime rt = Runtime.getRuntime();
	public static String userName = System.getProperty("user.name");
	public static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	public static Process process;
	public static String[] commText = new String[numArray];
	// Processing Stream

	public static void proc() throws IOException, InterruptedException {
		// CMD-String Array Settings
		commText[0] = "/changelog";
		commText[1] = "/about";
		commText[2] = "/help";
		commText[3] = "/clear";
		commText[4] = "/acknowledgements";
		commText[5] = "/font";
		commText[6] = "/api";
		commText[7] = "/save";
		commText[8] = "/saveLog";
		commText[9] = "/delete";
		commText[10] = "/info";
		commText[11] = "/mindterm";
		commText[12] = "/terminate";
		commText[13] = null;
		commText[14] = null;
		commText[15] = "quit";

		// Command Parsing
		preoperand = input.getText();
		append(computername + "~ $ " + preoperand);
		input.setText(null);
		operand = preoperand.split(" ");

		// Command Evaluation
		if (operand[0].equals(commText[0])) {
			com.me.ifly6.Commands.InfoMethods.changelog();
		}
		if (operand[0].equals(commText[1])) {
			com.me.ifly6.Commands.InfoMethods.about();
			log("'About' Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[2])) {
			com.me.ifly6.Commands.CoreMethods.help();
			log("Help Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[3])) {
			com.me.ifly6.Commands.InfoMethods.clear();
		}
		if (operand[0].equals(commText[4])) {
			com.me.ifly6.Commands.InfoMethods.acknowledgements();
		}
		if (operand[0].equals(commText[5])){
			int tmp = 11;
			tmp = java.lang.Integer.parseInt(operand[2]);
			Font font = new Font(operand[1], 0, tmp);
			display.setFont(font);
		}
		if (operand[0].equals(commText[6])){
			if (preoperand.equals(operand[0])){
				append("Current API Version: " + Info.api_version);
				for (int x = 0; x<10; x++){
					append("* " + Addons.api[x]);
				}
				log("API Processing Trigger Completed");
			} else {
				Addons.api();
				log("API Processing Trigger Completed");
			}
		}
		if (operand[0].equals(commText[7])){
			com.me.ifly6.Commands.AssortedMethods.save();
		}
		if (operand[0].equals(commText[8])){
			com.me.ifly6.Commands.AssortedMethods.saveLog();
		}
		if (operand[0].equals(commText[9])){
			com.me.ifly6.Commands.AssortedMethods.delete();
		}
		if (operand[0].equals(commText[10])){
			com.me.ifly6.Commands.AssortedMethods.info();
		}
		if (operand[0].equals(commText[11])){
			com.me.ifly6.Commands.AssortedMethods.mindterm();
		}
		if (operand[0].equals(commText[12])){
			com.me.ifly6.Commands.AssortedMethods.terminate();
		}
		/* if (operand[0].equals(commText[13])){
			
		}
		if (operand[0].equals(commText[14])){
			
		} */
		if (operand[0].equals(commText[15])){
			System.exit(0);
		}
		else if ((!operand[0].equals("bash"))) {
			com.me.ifly6.Commands.CoreMethods.exec();
			log("\nBASH COMMAND INVOKED: " + preoperand);
		}
	}
}
