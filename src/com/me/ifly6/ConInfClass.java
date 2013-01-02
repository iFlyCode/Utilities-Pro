package com.me.ifly6;

import java.awt.Font;

public class ConInfClass extends Console {
	// Name: Console Interface Class
	
	// Shared Resources
	private static final long serialVersionUID = 1L;
	protected static Runtime rt = Runtime.getRuntime();
	protected static String userName = System.getProperty("user.name");
	protected static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	protected static Font font = new Font("Monaco", 0, 10);

	// Standardised I/O Processing
	public static void append(String in){
		Console.output.append("\n" + in);
	}
	public static void append(int in){
		Console.output.append("\n" + in);
	}
	public static void log(String in){
		Console.log.append("\n" + in);
	}
	public static String getText(){
		return output.getText();
	}
	public static void setText(String in){
		output.setText(in);
	}
	public static void term_proc(){
	TextComm.process.destroy();
	}
}
