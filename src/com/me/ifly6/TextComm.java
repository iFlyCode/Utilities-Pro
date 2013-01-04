package com.me.ifly6;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextComm extends ConsoleIf {
	// Name: Text Commands (Executes Necessary Text Data from Console/anything else)

	private static final long serialVersionUID = 1L;
	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	public static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	public static Process process;
	static String[] commText = new String[10];
	// Processing Stream

	public static void proc() throws IOException {
		// CMD-String Array Settings
		commText[0] = "/changelog";
		commText[1] = "/copyright";
		commText[2] = "/help";
		commText[3] = "/clear";
		commText[4] = "/acknowledgements";
		commText[5] = "/font";
		commText[6] = "/api";

		// Command Parsing
		preoperand = input.getText();
		append(computername + "~ $ " + preoperand);
		input.setText(null);
		operand = preoperand.split(" ");

		// Command Evaluation
		if (operand[0].equals(commText[0])) {
			changelog();
		}
		if (operand[0].equals(commText[1])) {
			ConsoleIf.append(Info.copyright);
			log("\nCopyright Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[2])) {
			help();
			log("\nHelp Processing Trigger Invoked");
		}
		if (operand[0].equals(commText[3])) {
			Console.output.setText(starter);
			log("\nCommand to Clear Screen Invoked");
		}
		if (operand[0].equals(commText[4])) {
			acknowledgements();
		}
		if (operand[0].equals(commText[5])){
			int tmp = 11;
			tmp = java.lang.Integer.parseInt(operand[2]);
			Font font = new Font(operand[1], 0, tmp);
			output.setFont(font);
		}
		if (operand[0].equals(commText[6])){
			if (preoperand.equals(operand[0])){
				append("This is the current list of Plugins:");
				append(Info.plugins);
			} else {
				Addons.api();
			}
		}
		else {
			if ((!operand[0].equals("bash"))) {
				exec();
				log("\nBASH COMMAND INVOKED: " + preoperand);
			}
		}
	}

	// EXECUTION STREAM
	public static void exec() throws IOException {
		Runnable runner = new Runnable() {
			public void run() {

				// Output Stream
				ProcessBuilder builder = new ProcessBuilder(operand);
				try {
					process = builder.start();
					log.append("Execution of Operand Beginning.");
				} catch (IOException e) { log("ProcessBuilder Error: IOException"); }
				InputStream stderr = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				BufferedReader br = new BufferedReader(isr);
				String line;
				try {
					while ((line = br.readLine()) != null) { append(line); }
				} catch (IOException e) { }

				// Error Stream
				InputStream stderr1 = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1);
				BufferedReader br1 = new BufferedReader(isr1);
				String line1 = null;
				try {
					while ((line1 = br1.readLine()) != null) { append(line1); }
				} catch (IOException e) { }
			}
		};
		new Thread(runner).start();
	}

	/* THE CORE FUNCTIONS METHODS. */

	public static void changelog() throws IOException{
		String userName = System.getProperty("user.name");
		File folder = new File("/Users/" + userName + "/Library/Application Support/iUtilities");
		folder.mkdirs();
		String[] url = { "curl","-o", IUTILITIES_DIR + "/changelog.txt",
		"http://ifly6.no-ip.org/iUtilities/changelog.txt" };
		rt.exec(url);
		String r = "\n";
		FileReader fstream = new FileReader(IUTILITIES_DIR + "/changelog.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null) {
			ConsoleIf.append(r); }
		br.close();
		log("Changelog Processing Trigger Completed");
	}
	public static void acknowledgements() throws IOException{
		File folder = new File(IUTILITIES_DIR);
		folder.mkdirs();
		String[] url = { "curl", "-o", IUTILITIES_DIR + "/acknowledgements.txt",
		"http://ifly6.no-ip.org/iUtilities/acknowledgements.txt" };
		rt.exec(url);
		String r = "\n";
		FileReader fstream = new FileReader("/Users/" + userName + "/Library/Application Support/iUtilities/acknowledgements.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null){
			ConsoleIf.append(r); }
		br.close();
		log("Acknowledgements Processing Trigger Completed");
	}
	public static void help() throws IOException{
		for (int x = 0; x<10; x++){
			if (!(commText[x].equals(null))){
				append(commText[x]);
			}
		}
		log("Help Processing Trigger Completed");
	}
}
