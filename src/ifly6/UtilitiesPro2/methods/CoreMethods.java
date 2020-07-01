package ifly6.UtilitiesPro2.methods;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import ifly6.UtilitiesPro2.copy.TextProc;

/**
 * Contains methods which are vital to the operation of the programme. These methods are of the highest importance, and
 * should only be modified with significant thought on the consequences of their change.
 *
 * @since 2.2_01
 * @deprecated
 */
@Deprecated
public class CoreMethods extends TextProc {

	private static final long serialVersionUID = 1L;

	// EXECUTION STREAM
	public static void exec() throws IOException {
		Runnable runner = new Runnable() {
			@Override
			public void run() {

				// Output Stream
				ProcessBuilder builder = new ProcessBuilder(operand);
				try {
					process = builder.start();
					log("Execution of Operand Beginning.");
				} catch (IOException e) {
					log("ProcessBuilder Error: IOException");
				}
				InputStream stdout = process.getInputStream();
				InputStreamReader inRead = new InputStreamReader(stdout);
				Scanner scan = new Scanner(inRead);
				while (scan.hasNextLine()) {
					out(scan.nextLine());
				}
				scan.close();

				// Error Stream
				InputStream stderr = process.getErrorStream();
				InputStreamReader isr1 = new InputStreamReader(stderr);
				Scanner scan1 = new Scanner(isr1);
				while (scan1.hasNextLine()) {
					out(scan1.nextLine());
				}
				scan1.close();
			}
		};
		new Thread(runner).start();
	}

	public static void helpList() throws IOException {
		for (int x = 0; x < 20; x++) {
			if (!(commText[x].equals(null))) {
				out("* " + commText[x]);
			}
		}
		log("Help Processing Trigger Completed");
	}

	public static void terminate() {
		log("\nTermination of Dynamic Programme Switched");
		term_proc();
	}
}
