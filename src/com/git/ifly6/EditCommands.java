package com.git.ifly6;

/**
 * All Commands relevant to "Edit" are located here.
 * 
 * @author ifly6
 * @since 3.0
 */
public class EditCommands {

	/**
	 * Calls the clearText method in console, with input of 1
	 * 
	 * @see com.git.ifly6.Console#consoleClear
	 */
	public static void consoleClear() {
		Console.clearText(1);
	}

	/**
	 * Calls the clearText method in console, with input of 2
	 * 
	 * @see com.git.ifly6.Console#consoleClear
	 */
	public static void logClear() {
		Console.clearText(2);
	}
}
