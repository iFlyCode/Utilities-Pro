package com.me.ifly6;

public class Addons extends ConsoleIf {
	// Name: Managing Class for Add-ons
	
	private static final long serialVersionUID = 1L;
	public static String[] api = new String[numArray];

	public static void api(){
		// Processing of API Commands
		// This API is now deprecated until it is finished being written.
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