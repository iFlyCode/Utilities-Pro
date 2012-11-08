package com.me.ifly6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ToolbarProc extends ConInfClass {
	public static void save(String[] args) throws IOException {
		log("Output Saving System Invoked.");
		String report = getText();
		long time = System.currentTimeMillis();
		File folder = new File(IUTILITIES_DIR);
		folder.mkdirs();
		Writer output = null;
		File file = new File("/Users/" + userName + "/Library/Application Support/iUtilities/report" + time + ".txt");
		output = new BufferedWriter(new FileWriter(file));
		output.write(report);
		output.close();
		append("Contents Exported.");
	}
}
