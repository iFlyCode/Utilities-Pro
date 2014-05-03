package com.git.ifly6.UtilitiesPro3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Programme contains all relevant scripts to the File menu in the GUI.
 * 
 * @author ifly6
 * @since 3.0
 */
public class FileCommands extends Utilities_Pro {

	/**
	 * Method to manage all things regarding the Utilities Pro configuration file. Which function therein is selected
	 * using the provided integer, which.
	 * 
	 * @since 3.0_dev06, though component parts (1,2) are from 3.0_dev02 and 2.3, respectively.
	 * @param which
	 *            an integer which chooses which function to use on the configuration. There are three possibilities. 1)
	 *            Open the configuration in Finder, 2) Delete the configuration folder, 3) Generate default
	 *            configuration.
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
			mkdir();

			try {
				String config = "Default";
				FileWriter fstream = new FileWriter(UtilitiesPro_DIR + "/config.txt");
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
	 * Configuration Changer and Manager. Added for 3.3, the Interface Update.
	 * 
	 * @since 3.3
	 * @param line
	 *            - the line at which we are going to write the data to. Each line means something different in the
	 *            configuration format.
	 * @param contents
	 *            - the contents that we are going to write to the line. The contents determine what the setting for a
	 *            certain thing <b>is</b> in the configuration.
	 */
	static void configChange(int line, String contents) {
		ArrayList<String> fileCont = new ArrayList<String>();
		log("Writing to: line " + line + ", " + contents);

		// Code is copied from iFlyCode's JavaPy.
		try {
			FileReader configRead = new FileReader(UtilitiesPro_DIR + "/config.txt");
			Scanner scan = new Scanner(configRead);
			fileCont.add(scan.nextLine());
			fileCont.set(line, contents);

			String rewrite = contents.toString();
			FileWriter fstream = new FileWriter(UtilitiesPro_DIR + "/config.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(rewrite);
			out.close();
			scan.close();
		} catch (FileNotFoundException e) { // Show Error if there is no configuration.
			out("As configuration does not exist, we are generating a configuration file.");
			configManage(3);
		} catch (IOException e) { // Show Error for IO Exceptions.
			String temp = "Error in I/O Stream. Configuration Change Failed.";
			out(temp);
			log(temp);
		}
	}

	/**
	 * This method chooses which JTextArea to export, then exports it.
	 * 
	 * @author ifly6
	 * @param which
	 *            decides which JTextArea to export to file. 1 = Export the outText JTextArea. 2 = Export the logText
	 *            JTextArea.
	 * @since 3.0_dev04 (integrated from two separate commands)
	 */
	static void export(int which) {
		switch (which) {
		case 1:
			String outFile = Utilities_Pro.getOutText().getText();
			log("Output Export Invoked.");
			mkdir();
			Writer writer = null;
			File file = new File(UtilitiesPro_DIR + "/report_display-out" + new Date() + ".txt");
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
			file = new File(UtilitiesPro_DIR + "/report_display-out" + new Date() + ".txt");
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

	/**
	 * Part of the Interface Update, where we add a configuration manager to load and set configurations.
	 * 
	 * @since 3.3
	 */
	static void configHandler() {

	}
}
