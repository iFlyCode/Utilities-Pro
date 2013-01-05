package com.me.ifly6;

import java.awt.Font;

public class ConsoleIf extends Console {
	// Name: Console Interface Class

	// Shared Resources
	private static final long serialVersionUID = 1L;
	protected static Runtime rt = Runtime.getRuntime();
	protected static String userName = System.getProperty("user.name");
	protected static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	protected static Font font = new Font("Monaco", 0, 10);

	// Standardised I/O Processing
	public static void append(String in){
		Console.display.append("\n" + in);
		Console.output.append("\n" + in);
	}
	public static void append(int in){
		Console.display.append("\n" + in);
		Console.output.append("\n" + in);
	}
	public static void log(String in){
		Console.log.append("\n" + in);
	}
	public static void term_proc(){
		TextProc.process.destroy();
	}
	public static void clear() {
		Console.display.setText(null);
		Console.output.setText(null);
		Console.log.setText(null);
	}
}
