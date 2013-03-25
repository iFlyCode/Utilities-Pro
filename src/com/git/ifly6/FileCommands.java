package com.git.ifly6;

/**
 * @author ifly6
 * @since 3.0
 * ALL Toolbar>File Commands go here.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class FileCommands extends Console {

	public static void OpenConfig() {
		String[] openConfig = {"open", UtilitiesPro_DIR};
		try {
			rt.exec(openConfig);
		} catch (IOException e) { log("Open Failed"); }  
	}

	public static void DeleteConfig() {
		String[] delConfig = {"rm -rf", UtilitiesPro_DIR};
		try {
			rt.exec(delConfig);
		} catch (IOException e) { log("Config Delete Failed"); }  
	}

	/**
	 * This method chooses which JTextArea to export, then exports it.
	 * @author ifly6
	 * @param which		decides which JTextArea to export to file
	 * @since 3.0 		(integrated from two seperate commands)
	 */
	public static void export(int which){
		if (which == 1){
			String outFile = Console.getOutText();
			log("Output Export Invoked.");
			mkdir();
			Writer writer = null;
			File file = new File(UtilitiesPro_DIR  + "/report_display-out" + new Date() + ".txt");
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(outFile);
				writer.close();
			} catch (IOException e) { log("Output Export Failed"); }
			append("Contents Exported to " + UtilitiesPro_DIR);
		}
		if (which == 2){
			String outFile = Console.getOutText();
			log("Log Export Invoked.");
			mkdir();
			Writer writer = null;
			File file = new File(UtilitiesPro_DIR + "/report_display-out" + new Date() + ".txt");
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(outFile);
				writer.close();
			} catch (IOException e) { log("Log Export Failed"); }
			append("Contents Exported to " + UtilitiesPro_DIR);
		}
	}
}
