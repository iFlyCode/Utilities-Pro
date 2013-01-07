package com.me.ifly6;

import java.awt.Font;
import java.io.IOException;

import com.me.ifly6.methods.*;

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
		commText[5] = "/font [size]";
		commText[6] = null;
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
			InfoMethods.changelog();
			log("Changelog Called");
		}
		if (operand[0].equals(commText[1])) {
			InfoMethods.about();
			log("'About' Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[2])) {
			CoreMethods.helpList();
			log("Help Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[3])) {
			GraphicsMethods.clear();
			log("JTextAreas Cleared");
		}
		if (operand[0].equals(commText[4])) {
			InfoMethods.acknowledgements();
			log("Acknowledgements Called");
		}
		if (operand[0].equals(commText[5])){
			int tmp = 11;
			tmp = java.lang.Integer.parseInt(operand[2]);
			Font font = new Font(operand[1], 0, tmp);
			display.setFont(font);
			log("Font changed");
		}
		/* if (operand[0].equals(commText[6])){

		} */
		if (operand[0].equals(commText[7])){
			InOutMethods.save();
			log("Saving Processing Trigger Called");
		}
		if (operand[0].equals(commText[8])){
			InOutMethods.saveLog();
			log("SavingLog Processing Trigger Called");
		}
		if (operand[0].equals(commText[9])){
			InOutMethods.delete();
			log("Deletion Processing Trigger Called");
		}
		if (operand[0].equals(commText[10])){
			InfoMethods.info();
			log("System Information Processing Trigger Called");
		}
		if (operand[0].equals(commText[11])){
			InOutMethods.mindterm();
			log("Mindterm Download Processing Trigger Called");
		}
		if (operand[0].equals(commText[12])){
			InOutMethods.terminate();
			log("Process Termination Processing Trigger Called");
		}
		/* if (operand[0].equals(commText[13])){

		}
		if (operand[0].equals(commText[14])){

		} */
		if (operand[0].equals(commText[15])){
			System.exit(0);
			log("System.exit(0)");
		}
		else if ((!operand[0].equals("bash"))) {
			CoreMethods.exec();
			log("\nBASH COMMAND INVOKED: " + preoperand);
		}
	}
}
