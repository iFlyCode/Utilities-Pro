package com.me.ifly6.methods;

import javax.swing.text.DefaultCaret;

import com.me.ifly6.*;

public class GraphicsMethods extends ConsoleIf{
	private static final long serialVersionUID = 1L;

	public static void clear() {
		log("All Screens and JTextAreas Cleared");
		ConsoleIf.clear();
	}

	public static void defaultCarat() {
		DefaultCaret caret = (DefaultCaret)display.getCaret();
		caret.setUpdatePolicy(2);
	}

	public static void viewswitch() {
		if (screen_state == 0){
			setText(Console.log.getText());
			screen_state = 1;
		}
		if (screen_state == 1){
			setText(Console.output.getText());
			screen_state = 0;
		}
	} 
}
