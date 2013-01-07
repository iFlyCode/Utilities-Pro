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
		DefaultCaret caret = (DefaultCaret)output.getCaret();
		caret.setUpdatePolicy(2);
	}
}
