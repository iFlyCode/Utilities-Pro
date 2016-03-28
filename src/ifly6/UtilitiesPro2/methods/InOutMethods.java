package ifly6.UtilitiesPro2.methods;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import ifly6.UtilitiesPro2.copy.TextProc;

/**
 * @since 2.2_01
 * @deprecated
 */
@Deprecated
public class InOutMethods extends TextProc {
	// Name: Non-Core Methods

	private static final long serialVersionUID = 1L;

	public static void save() throws IOException {
		log("Display Saving System Invoked.");
		mkdir();
		Writer writer = null;
		File file = new File("/Users/" + userName + "/Library/Application Support/Utilities Pro/report_display-out"
				+ System.currentTimeMillis() + ".txt");
		writer = new BufferedWriter(new FileWriter(file));
		writer.write(display.getText());
		writer.close();
		out("Contents Exported.");
	}

	public static void script() {
		out("** Function Not Yet Built");
		log.append("Script Look Executed. May or may not have run.");
		// Some stuff.
	}

	public static void mindterm() throws IOException {
		mkdir();
		log.append("\nMindterm Download Commenced.");
		String[] url = { "curl", "-o", "/Users/" + userName + "/Downloads/",
				"http://ifly6.no-ip.org/Public/mindterm.jar" };
		rt.exec(url);
		out("Mindterm Downloaded to: " + "/Users/" + userName + "/Downloads/"
				+ "\nThis is a full Java Based SSH/Telnet Client, capable of using SSH -D."
				+ "\nIt is however, not made by the Utilities Pro Team, and therefore, does not fall under our perview.");
	}

	public static void purge() throws IOException {
		append(computername + "~ $ purge");
		log("Inactive Memory Purged");
		String[] exec = { "purge" };
		rt.exec(exec);
		log("Inactive Memory Purged");
		out("Inactive Memory Purged");
	}

	public static void saveLog() throws IOException {
		log("Utilities Pro Log Readout Command Executed");
		mkdir();
		Writer writer = null;
		File file = new File(UtilitiesPro_DIR + "/report_log" + System.currentTimeMillis() + ".txt");
		writer = new BufferedWriter(new FileWriter(file));
		writer.write(log.getText());
		writer.close();
		out("Debug Contents Exported to File in: " + UtilitiesPro_DIR);
	}

	public static void delete() throws IOException {
		log("Utilities Pro Folder Deletion Commencing.");
		out("Utilities Pro Folder Deletion Commencing.");
		String[] delete = { "rm", "-rf", (UtilitiesPro_DIR) };
		rt.exec(delete);
	}

	public static void update() throws IOException {
		String temp = ("Beginning Update Sequence");
		log(temp);
		out(temp);
		mkdir();
		String[] url = { "curl", "-o", UtilitiesPro_DIR + "/Utilities Pro-latest.jar",
				"http://ifly6.no-ip.org/Utilities Pro/Utilities Pro-latest.jar" };
		rt.exec(url);
		log("Update Successful.");
		append("Utilities Pro is updated. New file Utilities Pro-latest.jar in ~/Library/Application Support/Utilities Pro");
	}
}
