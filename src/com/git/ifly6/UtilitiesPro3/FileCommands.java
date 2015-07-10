package com.git.ifly6.UtilitiesPro3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Date;
import java.util.Properties;

/**
 * Programme contains all relevant scripts to the File menu in the GUI.
 *
 * @author ifly6
 * @since 3.0
 */
public class FileCommands extends Utilities_Pro {

	/**
	 * Deletes the configuration file if marked 'false'. If marked 'true', it deletes the entire Utilities Pro folder in
	 * Application Support.
	 *
	 * @since 3.3_dev05
	 */
	public static void deleteConfig(boolean all) {
		ExecEngine executor = new ExecEngine();

		if (all) {
			log("Deleting Configuration");
			String[] delConfig = { "rm", "-rf", UtilitiesPro_DIR };
			executor.exec(delConfig);
		} else {
			String[] delConfig = { "rm", UtilitiesPro_DIR + "/config.properties" };
			executor.exec(delConfig);
		}
	}

	/**
	 * Open the configuration folder.
	 */
	public static void openConfig() {
		String[] command = { "open", UtilitiesPro_DIR };
		new ExecEngine().exec(command);
	}

	/**
	 * Creates a configuration file with the default values.
	 *
	 * @since 3.3_dev05
	 */
	protected static void configGen() {
		Properties prop = new Properties();

		try {
			FileOutputStream output = new FileOutputStream(UtilitiesPro_DIR + "/config.properties");

			// Set Default Values
			prop.setProperty("Look&Feel", "System");
			prop.setProperty("QuitFunction", "persist");

			// Save Properties
			prop.store(output, "== Utilities Pro Properties ==");
			output.close();
		} catch (IOException e) {
			out("Could not Generate Properties. IOException.");
			log("Could not Generate Properties. IOException.");
		}
	}

	/**
	 * Part of the Interface Update, where we add a configuration manager to load and set configurations.
	 *
	 * @since 3.3_dev05
	 */
	protected static void configHandler(String key, String value) {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(UtilitiesPro_DIR + "/config.properties");

			// Set
			prop.setProperty(key, value);

			// Save
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

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
			mkdirs();
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
			mkdirs();
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
}
