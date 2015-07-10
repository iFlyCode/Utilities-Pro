package com.git.ifly6.UtilitiesPro3;

/**
 * Contains all relevant scripts to the Script menu in the programme.
 *
 * @author ifly6
 * @since 3.0 All Toolbar>Script Commands go here
 */
public class ScriptCommands extends Utilities_Pro {

	/**
	 * Method shows Finder Quit button and all hidden files.
	 *
	 * @since 3.3_dev03
	 */
	static void finderConfig(boolean status) {
		ExecEngine executor = new ExecEngine();

		String truths = "false";
		if (status) {
			truths = "true";
		}
		System.out.println(truths);

		String[] finderHidden = { "defaults", "write", "com.apple.Finder", "AppleShowAllFiles", truths };
		String[] finderQuit = { "defaults", "write", "com.apple.Finder", "QuitMenuItem", truths };

		executor.exec(finderHidden);
		executor.exec(finderQuit);

		executor.exec("killall Finder");
	}

	/**
	 * Method runs a Runtime section to purge inactive memory. It relies on a command inside OSX, called purge.
	 *
	 * @since 2.3_dev3
	 */
	static void purge() {
		if (System.getProperty("os.name").startsWith("10.8")) {
			out("Please wait. Purging inactive memory cache...");
			new ExecEngine().exec("purge");
			out("Purge Complete.");
		} else {
			out("This computer uses Compressed Memory and cannot be purged without super-user authorisation.", true);
		}
	}

	/**
	 * It turns off then 500 milliseconds later, turns on wireless adaptor (en0) for the computer. This is nearly word
	 * for word copied from iUtilities v1.0's implementation
	 *
	 * @since iUtilities v1.0
	 */
	static void wireless() {
		try {
			new ExecEngine().exec("networksetup -setairportpower en1 off");
			Thread.sleep(500);
			new ExecEngine().exec("networksetup -setairportpower en1 on");
		} catch (InterruptedException e) {
			log("Airport Restart Failed");
		}
		String outPut = ("Airport Connection Restarted.");
		out(outPut);
		log(outPut);
	}

	/**
	 * Method used to download the programme "Mindterm" from mirror at ifly6.no-ip.org
	 *
	 * @since 2.1
	 * @see {@link http://www.cryptzone.com/products/mindterm/#editionsstart}
	 */
	static void mindterm() {
		log("Mindterm Download Commenced.");
		new ExecEngine().download("http://ifly6.no-ip.org/Public/mindterm.jar", Downloads_DIR);
		out("Mindterm Downloaded to: "
				+ Downloads_DIR
				+ "\nThis is a full Java Based SSH/Telnet Client, capable of creating an SSH tunnel, and using it as a SOCKS proxy."
				+ "\nIt is, however, not made by the Utilities Pro Team, and therefore, does not fall under our perview.");
	}
}
