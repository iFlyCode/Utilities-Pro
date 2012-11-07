package com.me.ifly6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Core {

	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	private static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";

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
		while ((r = br.readLine()) != null)
			Console.output.append("\n " + r);
		Console.log.append("\nChangelog Processing Trigger Invoked.");
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
		while ((r = br.readLine()) != null)
			Console.output.append("\n " + r);
		Console.log.append("\nAcknowledgements Processing Trigger Invoked");
	}
}
