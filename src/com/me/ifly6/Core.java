package com.me.ifly6;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Core extends Console{

	private static final long serialVersionUID = 1L;
	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	private static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";

	public static void processing(String[] args) throws InterruptedException, IOException {
		textStep1 = input.getText();
		output.append("\n" + computername + "~ $ " + textStep1);
		input.setText(null);
		textStep2 = textStep1.split(" ");

		// Sub-commands
		if (textStep2[0].equals("changelog")) {
			Core.changelog(null);
		}
		if (textStep2[0].equals("copyright")) {
			output.append("\n" + Info.copyright);
			log.append("\nCopyright Processing Trigger Invoked");
		}
		if (textStep2[0].equals("help")) {
			output.append(helpstring);
			log.append("\nHelp Processing Trigger Invoked");
		}
		if (textStep2[0].equals("/clear")) {
			output.setText(starter);
			log.append("\nCommand to Clear Screen Invoked");
		}
		if (textStep2[0].equals("acknowledgements")) {
			Core.acknowledgements(null);
		}
		if (textStep2[0].equals("/font")){
			int tmp;
			if (textStep2[2].equals(null)){
				tmp = 11;
			}
			tmp = java.lang.Integer.parseInt(textStep2[2]);
			Font font = new Font(textStep2[1], 0, tmp);
			output.setFont(font);
		}

		// ProcessBuilder Calling System
		else { 
			if ((!textStep2[0].equals("bash"))) {
				exec(null);
				log.append("\nBASH COMMAND INVOKED: " + textStep1);
			}
		}	
	}

	// EXECUTION STREAM
	public static void exec(String[] args) throws IOException{
		// Output Stream
		ProcessBuilder builder = new ProcessBuilder(textStep2);
		Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			output.append("\n" + line);
		}
		// Error Stream
		InputStream stderr1 = process.getErrorStream();
		InputStreamReader isr1 = new InputStreamReader(stderr1);
		BufferedReader br1 = new BufferedReader(isr1);
		String line1 = null;
		while ((line1 = br1.readLine()) != null)
			output.append("\n " + line1);
	}
	
	/*
	 *  THE CORE FUNCTIONS METHODS.
	 */
	
	public static void changelog(String[] args) throws IOException{
		String userName = System.getProperty("user.name");
		File folder = new File("/Users/" + userName + "/Library/Application Support/iUtilities");
		folder.mkdirs();
		String[] url = { "curl","-o", IUTILITIES_DIR + "/changelog.txt",
		"http://ifly6server.no-ip.org/iUtilities/changelog.txt" };
		rt.exec(url);
		String r = "\n";
		FileReader fstream = new FileReader(IUTILITIES_DIR + "/changelog.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null) {
			Console.output.append("\n " + r); }
		br.close();
		log.append("\nChangelog Processing Trigger Invoked.");
	}
	public static void acknowledgements(String[] args) throws IOException{
		File folder = new File(IUTILITIES_DIR);
		folder.mkdirs();
		String[] url = { "curl", "-o", IUTILITIES_DIR + "/acknowledgements.txt",
				"http://ifly6server.no-ip.org/iUtilities/acknowledgements.txt" };
		rt.exec(url);
		String r = "\n";
		FileReader fstream = new FileReader("/Users/" + userName + "/Library/Application Support/iUtilities/acknowledgements.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null){
			Console.output.append("\n " + r); }
		br.close();
		Console.log.append("\nAcknowledgements Processing Trigger Invoked");
	}
}
