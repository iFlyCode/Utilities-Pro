/* Copyright (c) 2017 ifly6
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

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
public class HelpCommands extends UtilitiesPro {

	/**
	 * Opens the GitHub versions page. Used to download an actual changelog, then read it, but that was changed as we
	 * stopped maintaining it. Uses same code from update().
	 *
	 * @since 1.2
	 */
	public static void changeLog() {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI("https://github.com/iFlyCode/Utilities-Pro/releases"));
			}
		} catch (IOException e) {
			log("Cannot Open GitHub Releases");
		} catch (URISyntaxException e) {
			log("Cannot Open GitHub Releases");
		}
	}

	/**
	 * Displays about text. Displays a long string, String about in a JOptionPane. It then calls the change-log method.
	 *
	 * @since 3.0_dev07
	 * @see HelpCommands#changelog()
	 */
	public static void about() {
		String about = ("Utilities Pro - " + UtilitiesPro.version + " '" + UtilitiesPro.keyword + "'" + "\n"
				+ "\nUtilities Pro is a Java Runtime/ProcessBuilder tapper. "
				+ "\nIt is to serve as a terminal in restricted enviornments, such as "
				+ "\nschools or universities. Tapping Java's ProcessBuilder or Runtime"
				+ "\ncommand system, its possible to bypass MCX, and most other"
				+ "\ncontrols on effective computer work.");
		JOptionPane.showMessageDialog(null, about);
		HelpCommands.changeLog();
	}

	/**
	 * Downloads an acknowledgements file from ifly6.no-ip.org. It then reads it and displays the output.
	 *
	 * @deprecated
	 * @since 1.3
	 */
	@Deprecated
	public static void acknowledgements() {
		try {
			ExecEngine.download("http://ifly6.no-ip.org/UtilitiesPro/acknowledgements.txt",
					UtilitiesPro_DIR + "/acknowledgements.txt");
			Thread.sleep(100);
			FileReader fstream;
			fstream = new FileReader(UtilitiesPro_DIR + "/acknowledgements.txt");
			Scanner scan = new Scanner(fstream);
			while (scan.hasNextLine()) {
				out(scan.nextLine());
			}
			scan.close();
			log("Acknowledgements Processing Trigger Completed");
		} catch (FileNotFoundException e) {
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Displays the contents of Utilities_Pro.commText, which (due to design) is the list of all internal commands for
	 * the programme.
	 *
	 * @see UtilitiesPro.commText
	 * @since 2.3
	 */
	public static void helpList() {
		out("== Utilities Pro Internal Commands ==");
		for (String element : commText) {
			out(" * " + element);
		}
	}

	/**
	 * Displays the hard-coded (and therefore, version specific) End User Licence Agreement for this programme.
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
	 * Opens (in default browser) a web site with an archive of A-Z OSX Bash Commands.
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

	/**
	 * Opens the release page for Utilities Pro on GitHub. This entire method was changed in 3.1_02. It now directs to
	 * the GitHub release section for Utilities Pro.
	 *
	 * @since 3.1_02
	 */
	public static void update() {
		out("Fetch the latest version from: https://github.com/iFlyCode/Utilities-Pro/releases");
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(new URI("https://github.com/iFlyCode/Utilities-Pro/releases"));
			}
		} catch (IOException e) {
			log("Cannot Open GitHub Releases");
		} catch (URISyntaxException e) {
			log("Cannot Open GitHub Releases");
		}
	}
}
