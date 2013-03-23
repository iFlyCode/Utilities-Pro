package com.me.ifly6.methods;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.text.DefaultCaret;

import com.me.ifly6.*;

public class GraphicsMethods extends ConsoleIf {
	private static final long serialVersionUID = 1L;

	// Note the CONSOLE GUI is not addressed here. This is for the Console ASCII graphics.
	
	public static void clear() {
		log("All Screens and JTextAreas Cleared");
		ConsoleIf.clear();
	}

	public static void defaultCarat() {
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(2);
	}
	public static void saveConfig(String config){
		// String configuration should either be "Default" or "CrossPlatformLAF"
		try{
			FileWriter fstream = new FileWriter(UtilitiesPro_DIR + "/config");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(config);
			out.close();
		} catch (Exception e){ log("Attempt to save LAF Config Failed."); }
	}
}
