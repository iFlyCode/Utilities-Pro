package com.me.ifly6;

import java.awt.Font;
import java.io.File;
import java.io.FileReader;

public class ConsoleIf extends Console {
	// Name: Console Interface Class

	// Shared Resourcess
	private static final long serialVersionUID = 1L;
	public static Runtime rt = Runtime.getRuntime();
	public static String userName = System.getProperty("user.name");
	public static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	public static Font font = new Font("Monaco", 0, 10);
	public static FileReader fstream;
	
	// ALL DATA TO PASS THRU THESE METHODS
	// Standardised I/O Processing
	public static void append(String in){
		Console.display.append("\n" + in);
	}
	public static void out(String in){
		Console.display.append("\n " + in);
	}
	public static void log(String in){ Console.log.append("\n" + in); }
	
	// Getter and Setter
	public static String getText(){ return display.getText(); }
	public static void setText(String in){ display.setText(in); }
	
	// Files and Clearing
	public static void term_proc(){
		TextProc.process.destroy();
	}
	public static void clear() {
		Console.display.setText(null);
		Console.log.setText(null);
	}
	public static void mkdir() {
		File folder = new File("/Users/" + userName + "/Library/Application Support/iUtilities");
		folder.mkdirs();
	}
}
