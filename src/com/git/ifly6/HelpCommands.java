package com.git.ifly6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * All Commands relevant to "Help" are located here.
 * 
 * @author ifly6
 * @since 3.0_dev06
 */
public class HelpCommands extends Console {

	/**
	 * Downloads a changelog file with the latest version stated at the top. It
	 * then reads the file and displays the output.
	 * 
	 * @since 1.2
	 */
	public static void changeLog() {
		try {
			String url = "curl -o "
					+ UtilitiesPro_DIR
					+ "/changelog.txt http://ifly6.no-ip.org/iUtilities/changelog.txt";
			ExecEngine.exec(url);
			Thread.sleep(500);
			FileReader fstream;
			fstream = new FileReader(UtilitiesPro_DIR + "/changelog.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				append(scan.nextLine());
			}
			log("Changelog Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		}
	}

	public static void about() {
		// TODO Auto-generated method stub

	}

	/**
	 * Downloads an acknowledgements file from ifly6.no-ip.org. It then reads it
	 * and displays the output.
	 * 
	 * @since 1.3
	 */
	public static void acknowledgements() {
		try {
			String[] url = { "curl", "-o",
					UtilitiesPro_DIR + "/acknowledgements.txt",
					"http://ifly6.no-ip.org/Utilities Pro/acknowledgements.txt" };
			ExecEngine.exec(url);
			Thread.sleep(500);
			FileReader fstream;
			fstream = new FileReader(
					"/Users/"
							+ userName
							+ "/Library/Application Support/Utilities Pro/acknowledgements.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				append(scan.nextLine());
			}
			log("Acknowledgements Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Displays the non-null contents of Console.commText.
	 * 
	 * @see Console.commText
	 * @since 2.3
	 */
	public static void helpList() {
		append("== Utilities Pro Internal Commands ==");
		for (String element : commText) {
			if (!(element.equals("null"))) {
				append("* " + element);
			}
		}
	}

	public static void licence() {
		// TODO Auto-generated method stub

	}

	/**
	 * Opens (in default browser) a website with an archive of A-Z OSX Bash
	 * Commands.
	 * 
	 * @since 3.0_dev06
	 */
	public static void bashHelp() {
		String[] command = { "open", "http://ss64.com/osx/" };
		ExecEngine.exec(command);
	}
}
