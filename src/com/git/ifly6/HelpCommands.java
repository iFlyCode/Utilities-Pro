package com.git.ifly6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class HelpCommands extends Console {

	public static void about() {
		try {
			String url = "curl -o "
					+ UtilitiesPro_DIR
					+ "/changelog.txt http://ifly6.no-ip.org/iUtilities/changelog.txt";
			TextCommands.exec(url);
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
}
