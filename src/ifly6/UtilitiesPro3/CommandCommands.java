/* Copyright (c) 2015 ifly6
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

package ifly6.UtilitiesPro3;

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
			ExecEngine.exec(input);
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
		ExecEngine.exec("ps -ax");
		String choice = JOptionPane.showInputDialog(null,
				"Please input a Process ID" + "\nfrom the choices in the Console", "");
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
			ExecEngine.exec(choice);
		}
	}
}
