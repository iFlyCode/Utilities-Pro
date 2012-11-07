package com.me.ifly6;

import java.awt.Font;

public class API extends Console {
	
	// Kyle Swartley is redeveloping the API. Please do not edit or touch.
	
	private static final long serialVersionUID = 1L;
	protected static Runtime rt = Runtime.getRuntime();
	protected static String userName = System.getProperty("user.name");
	protected static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	protected static Font font = new Font("Monaco", 0, 10);

	// Standardised Output Processing
	public static void append(String in){
		output.append("\n" + in);
	}
	public static void append(int in){
		output.append("\n" + in);
	}
	public static void log(String in){
		log.append("\n" + in);
	}
}
