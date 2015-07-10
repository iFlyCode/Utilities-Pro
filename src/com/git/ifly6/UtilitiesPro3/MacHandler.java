package com.git.ifly6.UtilitiesPro3;

import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

/**
 * Note: Please do not change anything with this class. It needs to stay Static. This class here is filled with
 * deprecated references, as the the methods of the Java implementation for OSX are gradually being faded out, to my
 * chagrin. Anyway, the application is given a handler for the ToolBar Menu under its name. We are going to handle the
 * About, Preferences, and Quit dialogues. Unfortunately, as the non-deprecated classes are not used by anyone
 * (apparently) and there is less documentation on them than on THIS project, it is generally useless. However, because
 * of our handling of the three buttons, they are to function. Anyway... the About dialogue should open a small dialogue
 * telling the world of the version and a short description. The preferences dialogue should open the preferences page
 * in UtilitiesPro_DIR. The quit system should immediately System.exit(0) the programme.
 *
 * @since 3.0_dev05
 * @see com.apple.eawt
 */

@SuppressWarnings("deprecation")
public class MacHandler extends ApplicationAdapter {

	/**
	 * Handles the Quit menu by System.exit(0).
	 *
	 * @since 3.0_dev05
	 * @param e
	 *            is not used
	 */
	@Override
	public void handleQuit(ApplicationEvent e) {
		TextCommands textParse = new TextCommands();
		textParse.quitHandler();
	}

	/**
	 * Handles the About argument by calling the helpCommands command, about.
	 *
	 * @since 3.0_dev05
	 * @param e
	 *            is not used
	 * @see com.git.ifly6.UtilitiesPro3.HelpCommands
	 */
	@Override
	public void handleAbout(ApplicationEvent e) {
		e.setHandled(true);
		HelpCommands.about();
	}

	/**
	 * Handles the Preferences argument by opening the configuration file.
	 *
	 * @since 3.0_dev05
	 * @param e
	 *            is not used
	 * @see com.git.ifly6.UtilitiesPro3.FileCommands
	 */
	@Override
	public void handlePreferences(ApplicationEvent e) {
		String[] input = { "open", Utilities_Pro.UtilitiesPro_DIR + "/config.txt" };
		new ExecEngine().exec(input);
	}
}