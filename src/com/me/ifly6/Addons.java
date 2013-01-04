package com.me.ifly6;

public class Addons extends ConsoleIf {
	private static final long serialVersionUID = 1L;
	public String[] api = new String[10];

	public static void api(){
		list();
		if (operand[1].equals("exec")){
			if (operand[2].equals("SimplePlugin")){
				com.me.ifly6.plugins.SimplePlugin.plugin(null);
			}
			if (operand[2].equals("DebugMenu")){
				com.me.ifly6.plugins.DebugMenu.main(null);
			}
		}
	}
	public static void list(){
		api[0] = "DebugMenu";
	}
}
