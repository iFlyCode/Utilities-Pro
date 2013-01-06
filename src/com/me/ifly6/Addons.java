package com.me.ifly6;

import com.me.ifly6.addons.*;

public class Addons extends ConsoleIf {
	// Name: Managing Class for Add-ons
	
	private static final long serialVersionUID = 1L;
	public static String[] api = new String[numArray];

	public static void api(){
		// Processing of API Commands
		if (operand[1].equals("exec")){
			if (operand[2].equals(api[0])){
				SimplePlugin.plugin();
			}
			if (operand[2].equals(api[1])){
				Runnable runner = new Runnable() {
					public void run() { DebugMenu.execute(); } };
				new Thread(runner).start();
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