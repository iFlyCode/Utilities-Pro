package com.me.ifly6.methods;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.me.ifly6.Addons;
import com.me.ifly6.Parametres;
import com.me.ifly6.TextProc;

public class CoreMethods extends TextProc{
	// Name: Core Methods Required for Functioning

	private static final long serialVersionUID = 1L;

	// EXECUTION STREAM
	public static void exec() throws IOException {
		Runnable runner = new Runnable() {
			public void run() {

				// Output Stream
				ProcessBuilder builder = new ProcessBuilder(operand);
				try {
					process = builder.start();
					log("Execution of Operand Beginning.");
				} catch (IOException e) { log("ProcessBuilder Error: IOException"); }
				InputStream stdout = process.getInputStream();
				InputStreamReader inRead = new InputStreamReader(stdout);
				Scanner scan = new Scanner(inRead);
				while (scan.hasNextLine()) {
					out(scan.nextLine());
				}

				// Error Stream
				InputStream stderr = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr);
				Scanner scan1 = new Scanner(isr1);
				while (scan1.hasNextLine()) {
					out(scan1.nextLine());
				}
			}
		};
		new Thread(runner).start();
	}

	public static void helpList() throws IOException{
		for (int x = 0; x<50; x++){
			if (!(commText[x].equals(null))){
				out("* " + commText[x]);
			}
		}
		log("Help Processing Trigger Completed");
	}

	public static void apiList() {
		if (preoperand.equals(operand[0])){
			append("Current API Version: " + Parametres.api_version);
			append("Type /api exec 'name' to execute Programmes");
			for (int x = 0; x<50; x++){
				if (!(Addons.api[x].equals(null))){
					out("* " + Addons.api[x]);
				}
			}
			log("API Processing Trigger Completed");
		} else {
			Addons.api();
			log("API Processing Trigger Completed, 'else' triggered.");
		}
	}

}
