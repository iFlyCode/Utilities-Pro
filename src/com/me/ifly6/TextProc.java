package com.me.ifly6;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextProc extends ConInfClass {

	private static final long serialVersionUID = 1L;
	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	public static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	public static Process process = null;

	public static void processing(String[] args) throws InterruptedException, IOException {
		preoperand = input.getText();
		ConInfClass.append(computername + "~ $ " + preoperand);
		input.setText(null);
		operand = preoperand.split(" ");

		// Sub-commands
		if (operand[0].equals("changelog")) {
			TextProc.changelog(null);
		}
		if (operand[0].equals("copyright")) {
			ConInfClass.append(Info.copyright);
			log("\nCopyright Processing Trigger Invoked");
		}
		if (operand[0].equals("help")) {
			TextProc.help(null);
			log("\nHelp Processing Trigger Invoked");
		}
		if (operand[0].equals("/clear")) {
			Console.output.setText(starter);
			log("\nCommand to Clear Screen Invoked");
		}
		if (operand[0].equals("acknowledgements")) {
			TextProc.acknowledgements(null);
		}
		if (operand[0].equals("/font")){
			int tmp;
			if (operand[2].equals(null)){
				tmp = 11;
			}
			tmp = java.lang.Integer.parseInt(operand[2]);
			Font font = new Font(operand[1], 0, tmp);
			output.setFont(font);
		}
		if (operand[0].equals("/api")){
			if (preoperand.equals(operand[0])){
				append("This is the current list of Plugins:");
				append(Info.plugins);
			} else {
				api(null);
			}
		}

		// ProcessBuilder Calling System
		else { 
			if ((!operand[0].equals("bash"))) {
				exec(null);
				log("\nBASH COMMAND INVOKED: " + preoperand);
			}
		}	
	}

	// EXECUTION STREAM
	public static void exec(String[] args) throws IOException{
		Runnable runner = new Runnable() {
			public void run() {
				// Output Stream
				ProcessBuilder builder = new ProcessBuilder(operand);
				try {
					process = builder.start();
				} catch (IOException e) { log("ProcessBuilder Error: IOException"); }
				InputStreamReader isr = new InputStreamReader(process.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line;
				try {
					while ((line = br.readLine()) != null) {
						append(line);
					}
				} catch (IOException e) { }
				// Error Stream
				InputStream stderr1 = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1);
				BufferedReader br1 = new BufferedReader(isr1);
				String line1 = null;
				try {
					while ((line1 = br1.readLine()) != null) {
						append(line1); }
				} catch (IOException e) { }
			}
		};
		new Thread(runner).start();
	}

	/* THE CORE FUNCTIONS METHODS. */

	public static void changelog(String[] args) throws IOException{
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
			ConInfClass.append(r); }
		br.close();
		log("\nChangelog Processing Trigger Invoked.");
	}
	public static void acknowledgements(String[] args) throws IOException{
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
			ConInfClass.append(r); }
		br.close();
		log("\nAcknowledgements Processing Trigger Invoked");
	}
	public static void help(String[] args) throws IOException{
		File folder = new File(IUTILITIES_DIR);
		folder.mkdirs();
		String[] url = { "curl", "-o", IUTILITIES_DIR + "/help.txt",
		"http://ifly6server.no-ip.org/iUtilities/help/2.2_02.txt" };
		rt.exec(url);
		String r = "\n";
		FileReader fstream = new FileReader(IUTILITIES_DIR +"/help.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null){
			ConInfClass.append(r); }
		br.close();
		log("\nHelp Processing Trigger Invoked");
	}
	public static void api(String[] args){
		if (operand[1].equals("SimplePlugin")){
			com.me.ifly6.plugins.SimplePlugin.plugin(null);
		}
		if (operand[1].equals("DebugMenu")){
			com.me.ifly6.plugins.DebugMenu.main(null);
		}
	}
}
