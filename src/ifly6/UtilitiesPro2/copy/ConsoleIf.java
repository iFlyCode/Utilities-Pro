package ifly6.UtilitiesPro2.copy;

import java.awt.Font;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

/**
 * Interface from all Data return for processing and data into the Console's
 * declarations. It functions as the traditional pathway for data from any
 * data's input into the JTextAreas associated with the Console. It also
 * contains useful methods which are called programme-wide. All classes
 * associated should extend this class at some point.
 * 
 * @since 2.1
 * @author ifly6
 * @deprecated
 */
@Deprecated
public class ConsoleIf extends Console {
	// Name: Console Interface Class

	// Shared Resources
	private static final long serialVersionUID = 1L;
	public static Runtime rt = Runtime.getRuntime();
	public static String userName = System.getProperty("user.name");
	public static final String UtilitiesPro_DIR = "/Users/" + userName
			+ "/Library/Application Support/Utilities Pro";
	public static Font font = new Font("Monaco", 0, 10);
	public static FileReader fstream;

	/*
	 * ALL DATA TO PASS THROUGH THESE METHODS Standardised I/O Processing
	 */

	/**
	 * Standard Display method. Displays a string (in), into the Console, with
	 * the necessary line return.
	 * 
	 * @param in
	 *            String to append with no space into Console.
	 */
	public static void append(String in) {
		Console.display.append("\n" + in);
	}

	/**
	 * Secondary Display method. Displays a string (in) into the Console, with
	 * the necessary line return, and a space.
	 * 
	 * @param in
	 *            String to append with a space into the Console.
	 */
	public static void out(String in) {
		Console.display.append("\n " + in);
	}

	/**
	 * Standard Method for giving data to the user in the Log. Auto-formats that
	 * string, with the necessary line break and a date/time.
	 * 
	 * @param in
	 *            String to be put into the log.
	 */
	public static void log(String in) {
		Console.log.append("\n" + new Date() + " " + in);
	}

	/**
	 * Gets a String with all the contents of the Console.
	 * 
	 * @return String (contents of Console)
	 */
	public static String getText() {
		return display.getText();
	}

	/**
	 * Sets the Console as a certain String.
	 * 
	 * @param in
	 */
	public static void setText(String in) {
		display.setText(in);
	}

	// Files and Clearing
	/**
	 * Destroys the process which is started in the CoreMethods Class.
	 */
	public static void term_proc() {
		TextProc.process.destroy();
	}

	/**
	 * Clears the Console and the Log.
	 */
	public static void clear() {
		Console.display.setText(null);
		Console.log.setText(null);
	}

	/**
	 * Creates the directories required for standard functioning.
	 */
	public static void mkdir() {
		File folder = new File("/Users/" + userName
				+ "/Library/Application Support/Utilities Pro");
		folder.mkdirs();
	}
}
