package com.git.ifly6.UtilitiesPro3;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * All Commands relevant to "Help" are located here.
 * 
 * @author ifly6
 * @since 3.0_dev06
 */
public class HelpCommands extends Utilities_Pro {

	/**
	 * Downloads a change-log file with the latest version stated at the top. It
	 * then reads the file and displays the output.
	 * 
	 * @since 1.2
	 */
	public static void changeLog() {
		try {
			ExecEngine.download(
					"http://ifly6server.no-ip.org/utilities-pro/changelog.txt",
					UtilitiesPro_DIR + "/changelog.txt");
			Thread.sleep(100);
			FileReader fstream;
			fstream = new FileReader(UtilitiesPro_DIR + "/changelog.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				out(scan.nextLine());
			}
			log("Changelog Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Displays about text. Displays a long string, String about in a
	 * JOptionPane. It then calls the change-log method.
	 * 
	 * @since 3.0_dev07
	 * @see com.git.ifly6.UtilitiesPro3.HelpCommands#changelog()
	 */
	public static void about() {
		String about = ("Utilities Pro - "
				+ Utilities_Pro.version
				+ " '"
				+ Utilities_Pro.keyword
				+ "'"
				+ "\n"
				+ "\nUtilities Pro is a Java Runtime/ProcessBuilder tapper. "
				+ "\nIt is to serve as a terminal in restricted enviornments, such as "
				+ "\nschools or universities. Tapping Java's ProcessBuilder or Runtime"
				+ "\ncommand system, its possible to bypass MCX, and most other" + "\ncontrols on effective computer work.");
		JOptionPane.showMessageDialog(null, about);
		HelpCommands.changeLog();
	}

	/**
	 * Downloads an acknowledgements file from ifly6.no-ip.org. It then reads it
	 * and displays the output.
	 * 
	 * @since 1.3
	 */
	public static void acknowledgements() {
		try {
			ExecEngine.download(
					"http://ifly6.no-ip.org/UtilitiesPro/acknowledgements.txt",
					UtilitiesPro_DIR + "/acknowledgements.txt");
			Thread.sleep(100);
			FileReader fstream;
			fstream = new FileReader(UtilitiesPro_DIR + "/acknowledgements.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				out(scan.nextLine());
			}
			log("Acknowledgements Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Displays the contents of Utilities_Pro.commText, which (due to design) is
	 * the list of all internal commands for the programme.
	 * 
	 * @see Utilities_Pro.commText
	 * @since 2.3
	 */
	public static void helpList() {
		out("== Utilities Pro Internal Commands ==");
		for (String element : commText) {
			out("* " + element);
		}
	}

	/**
	 * Displays the hardcoded (and therefore, version specific) End User Licence
	 * Agreement for this programme.
	 * 
	 * @since 2.3 (though there was one in iAccelerate)
	 */
	public static void licence() {
		out("== Utilities Pro Licence ==");
		out("* You accept all responsibility for anything caused by this programme.");
		out("* You will not change this programme to preform malicious work.");
		out("* You will credit the authors of this programme for anything based upon it.");
		out("* You will not use this programme to accomplish anything illegal.");
		out("* You will not claim warranty or mandate assistance from anyone on this programme.");
		out("* You will not distribute any modified copies under the authors' name.");
		out("* Any distribution of a modified version of this programme must be accompanied by public source.");
		out("* Any distribution of a modified version of this programme will be following this same licence.");
		out("By the way, if you actually read this, we are highly surprised.");

	}

	/**
	 * Opens (in default browser) a web site with an archive of A-Z OSX Bash
	 * Commands.
	 * 
	 * @since 3.0_dev06
	 */
	public static void bashHelp() {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI("http://ss64.com/osx/"));
			}
		} catch (IOException e) {
			log("Cannot Open Webpage");
		} catch (URISyntaxException e) {
			log("Cannot Open Webpage");
		}
	}

	public static void update() {
		out("Fetch the latest version from: https://github.com/iFlyCode/Utilities-Pro/releases");
		ExecEngine
				.exec("open https://github.com/iFlyCode/Utilities-Pro/releases");
	}
}
