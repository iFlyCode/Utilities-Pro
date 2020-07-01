package ifly6.UtilitiesPro2.copy;

import java.awt.Font;
import java.io.IOException;

import ifly6.UtilitiesPro2.methods.CoreMethods;
import ifly6.UtilitiesPro2.methods.GraphicsMethods;
import ifly6.UtilitiesPro2.methods.InOutMethods;
import ifly6.UtilitiesPro2.methods.InfoMethods;

/**
 * Text Processing, executes Necessary Text Data from Console/anything else
 *
 * @since 2.2
 * @author ifly6
 * @deprecated
 */
@Deprecated
public class TextProc extends ConsoleIf {

	private static final long serialVersionUID = 1L;
	public static Runtime rt = Runtime.getRuntime();
	public static String userName = System.getProperty("user.name");
	public static final String UtilitiesPro_DIR = "/Users/" + userName + "/Library/Application Support/";
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
		commText[5] = "/font [name] [size]";
		commText[6] = "/licence";
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
		if (commText[0].equals(operand[0])) {
			InfoMethods.changelog();
			log("Changelog Called");
		}
		if (commText[1].equals(operand[0])) {
			InfoMethods.about();
			log("'About' Processing Trigger Invoked");
		}
		if (commText[2].equals(operand[0])) {
			CoreMethods.helpList();
			log("Help Processing Trigger Invoked");
		}
		if (commText[3].equals(operand[0])) {
			GraphicsMethods.clear();
			log("JTextAreas Cleared");
		}
		if (commText[4].equals(operand[0])) {
			InfoMethods.acknowledgements();
			log("Acknowledgements Called");
		}
		if (commText[5].equals(operand[0])) {
			int tmp = 11;
			tmp = java.lang.Integer.parseInt(operand[2]);
			Font font = new Font(operand[1], 0, tmp);
			display.setFont(font);
			log("Font changed");
		}
		if (commText[6].equals(operand[0])) {
			InfoMethods.licence();
			log("EULA Triggered");
		}
		if (commText[7].equals(operand[0])) {
			InOutMethods.save();
			log("Saving Processing Trigger Called");
		}
		if (commText[8].equals(operand[0])) {
			InOutMethods.saveLog();
			log("SavingLog Processing Trigger Called");
		}
		if (commText[9].equals(operand[0])) {
			InOutMethods.delete();
			log("Deletion Processing Trigger Called");
		}
		if (commText[10].equals(operand[0])) {
			InfoMethods.info();
			log("System Information Processing Trigger Called");
		}
		if (commText[11].equals(operand[0])) {
			InOutMethods.mindterm();
			log("Mindterm Download Processing Trigger Called");
		}
		if (commText[12].equals(operand[0])) {
			CoreMethods.terminate();
			log("Process Termination Processing Trigger Called");
		}
		/* if (commText[13].equals(operand[0])) {
		 *
		 * } if (commText[14].equals(operand[0])) {
		 *
		 * } */
		if (commText[15].equals(operand[0])) {
			System.exit(0);
			log("System.exit(0)");
		}

		// Calls Core Method Command Execution Stream
		else {
			if ((!operand[0].equals("bash"))) {
				CoreMethods.exec();
				log("\nBASH COMMAND INVOKED: " + preoperand);
			}
		}
	}
}