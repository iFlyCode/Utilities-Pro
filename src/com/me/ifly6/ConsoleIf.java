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
	protected static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";
	protected static Font font = new Font("Monaco", 0, 10);
	protected static FileReader fstream;
	protected static BufferedReader br;
	
	// ALL DATA TO PASS THRU THESE METHODS
	// Standardised I/O Processing
	protected static void append(String in){
		Console.display.append("\n" + in);
	}
	protected static void append(int in){
		Console.display.append("\n" + in);
	}
	protected static void out(String in){
		Console.display.append("\n " + in);
	}
	protected static void out(int in){
		Console.display.append("\n " + in);
	}
	protected static void log(String in){ Console.log.append("\n" + in); }
	
	// Getter and Setter
	protected static String getText(){ return display.getText(); }
	protected static void setText(String in){ display.setText(in); }
	
	// Files and Clearing
	protected static void term_proc(){
		TextProc.process.destroy();
	}
	protected static void clear() {
		Console.display.setText(null);
		Console.log.setText(null);
	}
	protected static void mkdir() {
		File folder = new File("/Users/" + userName + "/Library/Application Support/iUtilities");
		folder.mkdirs();
	}
}
