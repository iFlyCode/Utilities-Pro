package com.me.ifly6;

import com.me.ifly6.addons.*;

public class Addons extends ConsoleIf {
	// Name: Managing Class for Addons
	
	private static final long serialVersionUID = 1L;
	public static String[] api = new String[numArray];

	public static void api(){
		// Processing of API Commands
		if (operand[1].equals("exec")){
			if (operand[2].equals(api[0])){
				SimplePlugin.plugin(null);
			}
			if (operand[2].equals(api[1])){
				DebugMenu.execute(null);
			}
		}
	}
	
	public static void array_fill(){
		// Fills arrays with correct commands on startup.
		api[0] = "SimplePlugin";
		api[1] = "DebugMenu";
		api[2] = null;
		api[3] = null;
		api[4] = null;
	}
}
