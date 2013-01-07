package com.me.ifly6.methods;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.text.DefaultCaret;

import com.me.ifly6.*;

public class GraphicsMethods extends ConsoleIf {
	private static final long serialVersionUID = 1L;

	public static void clear() {
		log("All Screens and JTextAreas Cleared");
		ConsoleIf.clear();
	}

	public static void defaultCarat() {
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(2);
	}
	
	public static void enableLogTab(){
		// TODO Enable the LogTab
	}

	public static void newConsole() {
		// TODO Find some way to add Tabs to this thing.
	}
	public static void saveConfig(String config){
		try{
			// Create file 
			FileWriter fstream = new FileWriter(IUTILITIES_DIR + "/config");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(config);
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
