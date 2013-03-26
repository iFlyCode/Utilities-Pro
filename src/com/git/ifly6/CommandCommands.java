package com.git.ifly6;

import javax.swing.JOptionPane;

/**
 * All commands related to the Toolbar Section "Command" are located here.
 * 
 * @author ifly6
 * @since 3.0_dev05
 */
public class CommandCommands extends Console {

	/**
	 * Utility used to destory the process running in a seperate thread that is
	 * created by the Process "process", usually by the
	 * ExecEngine.engine(String[]).
	 * 
	 * @since 2.3
	 * @see com.git.ifly6.ExecEngine.engine(String[])
	 */
	public static void terminateUtility() {
		Console.process.destroy();
	}

	/**
	 * Utility to try to terminate any programme by bombarding it with kill
	 * requests. It uses a for loop and the PID of the process.
	 * 
	 * @since 3.0_dev05
	 * @param which
	 *            is an int that selects what arbritrary process to terminate.
	 *            Integer must be referring to the PID of a process. The PID of
	 *            the process can be gotten by running through the PS AX
	 *            directory.
	 */
	public static void terminate(int which) {
		String PID = Integer.toString(which);
		for (int x = 0; x < 50; x++) {
			String[] input = { "kill", PID };
			ExecEngine.exec(input);
		}
	}

	/**
	 * Utility to choose which PID to send to the termination engine.
	 * 
	 * @since 3.0_dev06
	 * @return The integer which is given by user to the method
	 *         CommandCommands.terminate(int).
	 * @see CommandCommands.terminate(int)
	 */
	public static void terminateChoose() {
		ExecEngine.exec("ps -ax");
		String choice = JOptionPane.showInputDialog(null,
				"Please input a Process ID"
						+ "\nfrom the choices in the Console", "");
		terminate(Integer.parseInt(choice));
	}

	/**
	 * Utility to bombard the processor and the OS with a certain command,
	 * hoping that nothing will be able to stop it fast enough.
	 * 
	 * @since 3.0_dev06
	 * @see com.git.ifly6.terminate(int)
	 */
	public static void bombard() {
		String choice = JOptionPane.showInputDialog(null,
				"Please input a Process ID"
						+ "\nfrom the choices in the Console", "");
		for (int x = 0; x < 100; x++) {
			ExecEngine.exec(choice);
		}
	}
}
