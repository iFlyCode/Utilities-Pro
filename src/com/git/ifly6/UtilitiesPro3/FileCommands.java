package com.git.ifly6.UtilitiesPro3;

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
public class FileCommands extends Utilities_Pro {

	/**
	 * Method to manage all things regarding the Utilities Pro configuration file. Which function
	 * therein is selected using the provided integer, which.
	 * 
	 * @since 3.0_dev06, though component parts (1,2) are from 3.0_dev02 and 2.3, respectively.
	 * @param which
	 *            an integer which chooses which function to use on the configuration. There are
	 *            three possibilities. 1) Open the configuration in Finder, 2) Delete the
	 *            configuration folder, 3) Generate default configuration.
	 */
	static void configManage(int which) {
		switch (which) {
		case 1:
			log("Opening Configuration");
			String[] openConfig = { "open", UtilitiesPro_DIR };
			ExecEngine.exec(openConfig);
			break;
		case 2:
			log("Deleting Configuration");
			String[] delConfig = { "rm", "-rf", UtilitiesPro_DIR };
			ExecEngine.exec(delConfig);
			break;
		case 3:
			log("Generating Configuration");

			// The first line should either be "Default" or
			// "CrossPlatformLAF".
			try {
				String config = "Default" + "\n100";
				FileWriter fstream = new FileWriter(UtilitiesPro_DIR
						+ "/config.txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(config);
				out.close();
			} catch (IOException e) {
				log("Attempt to generate configuration Failed.");
			}
			break;
		default:
			out("No valid configuration command specified.");
			break;
		}
	}

	/**
	 * Configuration Changer and Manager. Added in 3.3, the Interface Update.
	 * 
	 * @since 3.3
	 */
	static void configChange() {

	}

	/**
	 * This method chooses which JTextArea to export, then exports it.
	 * 
	 * @author ifly6
	 * @param which
	 *            decides which JTextArea to export to file. 1 = Export the outText JTextArea. 2 =
	 *            Export the logText JTextArea.
	 * @since 3.0_dev04 (integrated from two separate commands)
	 */
	public static void export(int which) {
		switch (which) {
		case 1:
			String outFile = Utilities_Pro.getOutText().getText();
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
			out("Contents Exported to " + UtilitiesPro_DIR);
			break;
		case 2:
			outFile = Utilities_Pro.getLogText().getText();
			log("Log Export Invoked.");
			mkdir();
			writer = null;
			file = new File(UtilitiesPro_DIR + "/report_display-out"
					+ new Date() + ".txt");
			try {
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(outFile);
				writer.close();
			} catch (IOException e) {
				log("Log Export Failed");
			}
			out("Contents Exported to " + UtilitiesPro_DIR);
			break;
		default:
			out("No valid TextArea specified.");
			break;
		}
	}
}
