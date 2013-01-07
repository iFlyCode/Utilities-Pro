package com.me.ifly6;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ConsoleIf extends Console {
	// Name: Console Interface Class

	// Shared Resources
	private static final long serialVersionUID = 1L;
	protected static Runtime rt = Runtime.getRuntime();
	protected static String userName = System.getProperty("user.name");
	protected static final String IUTILITIES_DIR = "C:\\Documents and Settings\\" + userName + "\\UserData\\iUtilities";
	protected static Font font = new Font("Monaco", 0, 10);
	protected static FileReader fstream;
	protected static BufferedReader br;
	
	// ALL DATA TO PASS THRU THESE METHODS
	// Standardised I/O Processing
	public static void append(String in){
		Console.output.append("\n" + in);
	}
	public static void out(String in){
		Console.output.append("\n " + in);
	}
	public static void log(String in){ Console.log.append("\n" + in); }
	
	// Getter and Setter
	public static String getText(){ return output.getText(); }
	public static void setText(String in){ output.setText(in); }
	
	// Files and Clearing
	public static void term_proc(){
		TextProc.process.destroy();
	}
	public static void clear() {
		Console.output.setText(null);
		Console.log.setText(null);
	}
	public static void mkdir() {
		File folder = new File("C:\\Documents and Settings\\" + userName + "\\UserData\\iUtilities");
		folder.mkdirs();
	}
}
