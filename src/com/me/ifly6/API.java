package com.me.ifly6;

public class API extends Console {
	
	private static final long serialVersionUID = 1L;
	static Runtime rt = Runtime.getRuntime();
	static String userName = System.getProperty("user.name");
	private static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";

	// Standardised Output Processing
	public static void append(String in){
		output.append("\n" + in);
	}
	public static void log(String in){
		log.append("\n" + in);
	}
}
