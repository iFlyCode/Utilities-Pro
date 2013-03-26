package com.git.ifly6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/**
 * Programme contains all relevant scripts to the File menu in the GUI.
 * 
 * @author ifly6
 * @since 3.0
 */
public class FileCommands extends Console {

	/**
	 * Method to manage all things regarding the Utilities Pro configuration
	 * file. Which function therein is selected using the provided integer,
	 * which.
	 * 
	 * @since 3.0_dev06, though component parts (1,2) are from 3.0_dev02 and
	 *        2.3, respectively.
	 * @param which
	 *            an integer which chooses which function to use on the
	 *            configuration. There are three possibilities. 1) Open the
	 *            configuration in Finder, 2) Delete the configuration folder,
	 *            and 3) Generate configuration.
	 */
	public static void configManage(int which) {
		if (which == 1) {
			log("Opening Configuration");
			String[] openConfig = { "open", UtilitiesPro_DIR };
			ExecEngine.exec(openConfig);
		}
		if (which == 2) {
			log("Deleting Configuration");
			String[] delConfig = { "rm -rf", UtilitiesPro_DIR };
			ExecEngine.exec(delConfig);
		}
		if (which == 3) {
			log("Generating Configuration");
			// Generate Configuration
		}
	}

	/**
	 * Opens the folder in which all configuration files are kept for Utilities
	 * Pro.
	 * 
	 * @since 3.0_dev02
	 * @deprecated
	 */
	@Deprecated
	public static void OpenConfig() {
		String[] openConfig = { "open", UtilitiesPro_DIR };
		try {
			rt.exec(openConfig);
		} catch (IOException e) {
			log("Open Failed");
		}
	}

	/**
	 * Deletes the folder in which all configuration files are kept for
	 * Utilities Pro.
	 * 
	 * @since 2.3
	 * @deprecated
	 */
	@Deprecated
	public static void DeleteConfig() {
		String[] delConfig = { "rm -rf", UtilitiesPro_DIR };
		try {
			rt.exec(delConfig);
		} catch (IOException e) {
			log("Config Delete Failed");
		}
	}

	/**
	 * This method chooses which JTextArea to export, then exports it.
	 * 
	 * @author ifly6
	 * @param which
	 *            decides which JTextArea to export to file. 1 = Export the
	 *            outText JTextArea. 2 = Export the logText JTextArea.
	 * @since 3.0_dev04 (integrated from two separate commands)
	 */
	public static void export(int which) {
		if (which == 1) {
			String outFile = Console.getOutText();
			log("Output Export Invoked.");
			mkdir();
			Writer writer = null;
			File file = new File(UtilitiesPro_DIR + "/report_display-out"
					+ new Date() + ".txt");
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(outFile);
				writer.close();
			} catch (IOException e) {
				log("Output Export Failed");
			}
			append("Contents Exported to " + UtilitiesPro_DIR);
		}
		if (which == 2) {
			String outFile = Console.getOutText();
			log("Log Export Invoked.");
			mkdir();
			Writer writer = null;
			File file = new File(UtilitiesPro_DIR + "/report_display-out"
					+ new Date() + ".txt");
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(outFile);
				writer.close();
			} catch (IOException e) {
				log("Log Export Failed");
			}
			append("Contents Exported to " + UtilitiesPro_DIR);
		}
	}
}
