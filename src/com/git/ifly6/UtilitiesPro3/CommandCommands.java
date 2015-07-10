package com.git.ifly6.UtilitiesPro3;

import javax.swing.JOptionPane;

/**
 * All commands related to the Toolbar Section "Command" are located here.
 *
 * @author ifly6
 * @since 3.0_dev05
 */
public class CommandCommands extends Utilities_Pro {

	/**
	 * Utility used to destroy the process running in a separate thread that is created by the Process "process",
	 * usually by the ExecEngine.engine(String[]).
	 *
	 * @since 2.3
	 * @see com.git.ifly6.UtilitiesPro3.ExecEngine.engine(String[])
	 */
	static void terminateUtility() {
		Utilities_Pro.process.destroy();
	}

	/**
	 * Utility to try to terminate any programme by bombarding it with kill requests. It uses a for loop and the PID of
	 * the process.
	 *
	 * @since 3.0_dev05
	 * @param which
	 *            - is an integer that selects what arbitrary process to terminate. Integer must be referring to the PID
	 *            of a process. The PID of the process can be gotten by running through the PS AX directory.
	 */
	static void terminate(int which) {
		String PID = Integer.toString(which);
		for (int x = 0; x < 50; x++) {
			String[] input = { "kill", PID };
			new ExecEngine().exec(input);
		}
	}

	/**
	 * Utility to choose which PID to send to the termination engine.
	 *
	 * @since 3.0_dev06
	 * @return The integer which is given by user to the method CommandCommands.terminate(int).
	 * @see CommandCommands.terminate(int)
	 */
	static void terminateChoose() {
		new ExecEngine().exec("ps -ax");
		String choice = JOptionPane.showInputDialog(null, "Please input a Process ID"
				+ "\nfrom the choices in the Console", "");
		terminate(Integer.parseInt(choice));
	}

	/**
	 * Utility to bombard the processor and the OS with a certain command, hoping that nothing will be able to stop it
	 * fast enough.
	 *
	 * @since 3.0_dev06
	 * @see com.git.ifly6.UtilitiesPro3.terminate(int)
	 */
	static void bombard() {
		String choice = JOptionPane.showInputDialog(null, "Please input a bash command to try 250 times, repeatedly.",
				"");
		for (int x = 0; x < 250; x++) {
			new ExecEngine().exec(choice);
		}
	}
}
