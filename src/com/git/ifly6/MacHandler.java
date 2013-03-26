package com.git.ifly6;

import javax.swing.JOptionPane;

import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

/**
 * Note: Please do not change anything with this class. It needs to stay Static.
 * This class here is filled with deprecated references, as the the methods of
 * the Java implementation for OSX are gradually being faded out, to my chagrin.
 * Anyway, the application is given a handler for the ToolBar Menu under its
 * name. We are going to handle the About, Preferences, and Quit dialogues.
 * Unfortunately, as the non-deprecated classes are not used by anyone
 * (apparently) and there is less documentation on them than on THIS project, it
 * is generally usesless. However, because of our handling of the three buttons,
 * they are to function. Anyway... the About dialogue should open a small
 * dialogue telling the world of the version and a short description. The
 * preferences dialogue should open the preferences page in UtilitiesPro_DIR.
 * The quit system should immediately System.exit(0) the programme.
 * 
 * @author ifly6
 * @since 3.0_dev05
 * @see com.apple.eawt
 */

@SuppressWarnings("deprecation")
public class MacHandler extends ApplicationAdapter {

	@Override
	public void handleQuit(ApplicationEvent e) {
		System.exit(0);
	}

	@Override
	public void handleAbout(ApplicationEvent e) {
		e.setHandled(true);
		String about = ("Utilities Pro - "
				+ Console.version
				+ " '"
				+ Console.keyword
				+ "'\n"
				+ "\nUtilities Pro is a Java Runtime/ProcessBuilder tapper. "
				+ "\nIt is to serve as a terminal in restricted enviornments, such as "
				+ "\nschools or universities. Tapping Java's ProcessBuilder or Runtime"
				+ "\ncommand system, its possible to bypass MCX, and most other" + "\ncontrols on effective computer work.");
		JOptionPane.showMessageDialog(null, about);

		HelpCommands.changeLog();
	}

	@Override
	public void handlePreferences(ApplicationEvent e) {
		String[] input = { "open", Console.UtilitiesPro_DIR + "/config.txt" };
		ExecEngine.engine(input);
	}
}