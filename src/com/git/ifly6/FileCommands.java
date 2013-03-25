package com.git.ifly6;

import java.io.IOException;

public class FileCommands extends Console {

	public static void OpenConfig() {
		String[] openConfig = {"/usr/bin/open", UtilitiesPro_DIR};
		try {
			rt.exec(openConfig);
		} catch (IOException e) { }  
	}

	public static void DeleteConfig() {
		String[] delConfig = {"/usr/bin/open", UtilitiesPro_DIR};
		try {
			rt.exec(delConfig);
		} catch (IOException e) { }  
	}

	public static void exportOutput() {
		
	}

	public static void exportLog() {
		
	}

	/***
	 * @category Commands associated with the File command
	 * all goes here.
	 */

}
