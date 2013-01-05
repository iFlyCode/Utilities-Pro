package com.me.ifly6;

public class Addons extends ConsoleIf {
	// Name: Managing Class for Addons
	
	private static final long serialVersionUID = 1L;
	public static String[] api = new String[numArray];

	public static void api(){
		// Processing of API Commands
		if (operand[1].equals("exec")){
			if (operand[2].equals(api[0])){
				com.me.ifly6.plugins.SimplePlugin.plugin(null);
			}
			if (operand[2].equals(api[1])){
				com.me.ifly6.plugins.DebugMenu.main(null);
			}
		}
	}
	
	public static void array_fill(){
		// Fills arrays with correct commands on startup.
		api[0] = "SimplePlugin";
		api[1] = "DebugMenu";
		api[2] = "";
		api[3] = "";
		api[4] = "";
	}
}
