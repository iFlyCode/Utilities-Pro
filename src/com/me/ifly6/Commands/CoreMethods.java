package com.me.ifly6.Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.me.ifly6.Addons;
import com.me.ifly6.Info;
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
				InputStream stderr = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				BufferedReader br = new BufferedReader(isr);
				String line;
				try {
					while ((line = br.readLine()) != null) { out(line); }
				} catch (IOException e) { log("Buffered Reader Error: IOException"); }

				// Error Stream
				InputStream stderr1 = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr1);
				BufferedReader br1 = new BufferedReader(isr1);
				String line1 = null;
				try {
					while ((line1 = br1.readLine()) != null) { out(line1); }
				} catch (IOException e) { log("Buffered Reader Error: IOException"); }
			}
		};
		new Thread(runner).start();
	}

	public static void help() throws IOException{
		for (int x = 0; x<10; x++){
			if (!(commText[x].equals(null))){
				out("* " + commText[x]);
			}
		}
		log("Help Processing Trigger Completed");
	}

	public static void apiList() {
		if (preoperand.equals(operand[0])){
			append("Current API Version: " + Info.api_version);
			append("Type /api exec 'name' to execute Programmes");
			for (int x = 0; x<10; x++){
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
