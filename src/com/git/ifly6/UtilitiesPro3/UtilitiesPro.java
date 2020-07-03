/* Copyright (c) 2017 ifly6
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

package com.git.ifly6.UtilitiesPro3;

import com.apple.eawt.Application;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Main Class for Utilities Pro 3.x. It initialises the GUI and contains all relevant pieces of data fundamental to the
 * execution of the programme. Furthermore, it contains all necessary ActionListeners and GUI related methods (basically
 * integrating the older Utilities_Pro Interface, Parameters, and Utilities_Pro classes from the last major version of
 * Utilities Pro-2.x)
 *
 * @author ifly6
 * @version 3.x
 */
public class UtilitiesPro {

	/**
	 * Remembers all commands done in the session.
	 *
	 * @since 3.2
	 */
	static ArrayList<String> history = new ArrayList<>();

	/**
	 * The number which tells us where we are looking in the ArrayList.
	 *
	 * @since 3.2
	 */
	static int recall = history.size();

	/**
	 * Current Directory we are in. Change using our implementation of the CD command, located in TextCommands.
	 *
	 * @since 3.0_dev09.03
	 */
	public static String currentDir = System.getProperty("user.dir");

	/**
	 * List of all the internal commands inside a String Array. All unused commands should be stated as nulls.
	 */
	public static ArrayList<String> commText = new ArrayList<>();

	/**
	 * Used for greeting the user. It should be replaced from Unknown to the iNet name of the user inside
	 * Utilities_Pro.Main
	 */
	protected static String computername = "Unknown";

	/**
	 * Used in the all following File systems, as the user name of the user is not the same throughout all computers.
	 */
	public static String userName = System.getProperty("user.name");

	/**
	 * The place to put any files we download. For all OSX computers, it should be exactly the same.
	 */
	static String Downloads_DIR = System.getProperty("user.home") + "/Downloads/";

	/**
	 * TextField for the input of commands. When command engine is run, it retrieves the contents of this field, then
	 * processes it.
	 */
	private static TextField inputField;

	/**
	 * The Keyword is like "Sandy Bridge". There is a defined list of them. For 3.x, its is 3.0) iceland, 3.1) iceberg,
	 * 3.2) skyfall, 3.3) icepool, 3.4) icefield, 3.5) everest, 3.6) icemont, 3.7) icewell, 3.8) icedtea
	 */
	static String keyword = "icepool";

	/**
	 * JTextArea for the output of the log. Receives strings to append to the log from the method "log(String)"
	 */
	private static JTextArea logText;

	/**
	 * JTextArea for the output of the programme. Combines the Error and Output Streams into one field.
	 */
	private static JTextArea outText;

	/**
	 * Process is declared here to allow other classes to terminate that process should it be necessary.
	 */
	static Process process;

	/**
	 * A place in ~/Library/Application Support/ where we store all of our configuration files.
	 */
	static String UtilitiesPro_DIR = "/Users/" + userName + "/Library/Application Support/Utilities Pro";

	/**
	 * Naming system is: |major|.|minor|_|revision| or |major|.|minor|_|dev|<#> For the development number, it follows
	 * |major|.|minor|, but with no revisions.
	 */
	public static String version = "3.3_dev07";

	/**
	 * As it deals with the GUI's implementation (JTextArea), Java forces its location to be inside the GUI's
	 * declaration class.
	 *
	 * @author ifly6
	 * @since 3.0_dev02
	 * @param which
	 *            - integer value, determines which JTextArea to clear (1, outText; 2, logText; 3, inputField)
	 */
	public static void clearText(int which) {
		if (which == 1) {
			outText.setText("");
		}
		if (which == 2) {
			logText.setText("");
		}
		if (which == 3) {
			inputField.setText("");
		}
	}

	/**
	 * @since 3.0_dev07
	 * @param in
	 *            - String to append with the bash prompt to JTextArea outText. Also appends to logText.
	 */
	public static void printLoopback(String in) {

		// Get Name of Current Directory (as we now use Canonical names)
		String[] directories = currentDir.split("/");

		try {
			outText.append("\n" + computername + ":" + directories[directories.length - 1] + " $ " + in);
		} catch (Exception e) {
			outText.append("\n" + computername + ": $ " + in);
		}
	}

	/**
	 * Returns inputField.
	 *
	 * @since 3.0_dev05
	 * @return a string with the contents of TextArea inputField
	 */
	public static TextField getInputField() {
		return inputField;
	}

	/**
	 * Get and Return LogText for direct manipulation.
	 *
	 * @since 3.0_dev02
	 * @return JTextArea logText
	 */
	public static JTextArea getLogText() {
		return logText;
	}

	/**
	 * Get and return OutText for direct manipulation.
	 *
	 * @since 3.0_dev02
	 * @return JTextArea OutText
	 */
	public static JTextArea getOutText() {
		return outText;
	}

	/**
	 * @since 2.2_01
	 * @param in
	 *            - String to append (with a date) into the JTextArea logText
	 */
	public static void log(String in) {
		logText.append("\n" + new Date() + " " + in);
		System.out.println(new Date() + " " + in);
	}

	/**
	 * Launch the application. Executes on a pipeline, going first to read the GUI configuration file, with the Look and
	 * Feel of the GUI. It then populates the internal commands, finds the computer name, and initialises the GUI.
	 *
	 * @param inputArgs
	 *            - there are no command-line arguments.
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] inputArgs) {

		// Create Configuration Directory
		UtilitiesPro.mkdirs();

		// Set Properties before GUI Calls
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Utilities Pro");

		// Handling Mac Toolbar System
		Application macApp = Application.getApplication();
		MacHandler macAdapter = new MacHandler();
		macApp.addApplicationListener(macAdapter);
		macApp.setEnabledPreferencesMenu(true);

		// Deal with CSA should it be an LMSD computer
		if (computername.startsWith("HH-S") && userName.startsWith("s")) {
			ExecEngine.exec("killall CSA");
			UtilitiesPro.log("CSA terminated on Startup.");
		}

		// Populate the List of Internal Commands
		setCommands();

		// Get the name of the Computer
		try {
			computername = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			try {
				computername = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e1) {
				System.out.println("Localhost Name-Get failed.");
			}
		}

		// Launch the GUI.
		EventQueue.invokeLater(() -> {
			try {
				UtilitiesPro window = new UtilitiesPro();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Used to create (if necessary) all folders for Utilities Pro. Creates ~/Library/Application Support/Utilities Pro
	 * folder and verifies that ~/Downloads exists. This programme should be run on a Mac, as both are only applicable
	 * under the File Structure of one (or very similar Linux distributions).
	 *
	 * @author ifly6
	 * @since 2.2_01
	 */
	static void mkdirs() {
		// A list of folders to create.
		File[] folders = { new File(UtilitiesPro_DIR), new File(UtilitiesPro_DIR + "/plugins/"),
				new File(Downloads_DIR) };
		// Folders created.
		for (File dir : folders) {
			dir.mkdirs();
		}
	}

	/**
	 * Added in version 2.2_02 of Utilities Pro. Replaces 'append'. Difference is that it automatically formats the
	 * text.
	 *
	 * @since 2.2_02
	 * @param in
	 *            - String to append (with a space) into the JTextArea outText
	 */
	public static void out(String in) {
		outText.append("\n " + in);
		UtilitiesPro.outText.setCaretPosition(outText.getDocument().getLength());
	}

	/**
	 * Sets the arrayList of commands, as they are not hardcoded. This saves us a lot of problems. Don't remove it.
	 *
	 * @since 3.0_dev08
	 */
	private static void setCommands() {
		// ifly6 (2020-07-02) this legit stupid... just use an enum
		commText.add("/changelog");
		commText.add("/about");
		commText.add("/help");
		commText.add("/clear");
		commText.add("/licence");
		commText.add("/save");
		commText.add("/saveLog");
		commText.add("/config");
		commText.add("/mindterm");
		commText.add("/execscript");
		commText.add("/monitor");
		commText.add("/plugin");
		commText.add("/terminate");
		commText.add("/quit");
	}

	private JFrame frame;

	/**
	 * Create instance of the application.
	 */
	public UtilitiesPro() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(0, 0, 670, 735);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Utilities Pro " + version);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Console", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		inputField = new TextField();
		inputField.setFont(new Font("Monaco", Font.PLAIN, 11));
		inputField.setFocusTraversalKeysEnabled(false);
		panel.add(inputField, BorderLayout.SOUTH);
		inputField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();

				if (keyCode == KeyEvent.VK_ENTER) {
					TextCommands.processInputField();
				} else if (keyCode == KeyEvent.VK_UP) {
					try {
						recall--;
						inputField.setText(history.get(recall));
					} catch (IndexOutOfBoundsException ex) {
						recall = 0;
						try {
							inputField.setText(history.get(0));
						} catch (IndexOutOfBoundsException ex_again) {
							inputField.setText(null);
						}
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					try {
						recall++;
						inputField.setText(history.get(recall));
					} catch (IndexOutOfBoundsException ex) {
						recall = history.size();
						inputField.setText(null);
					}
				} else if (keyCode == KeyEvent.VK_TAB) {
					String reinput = TextCommands.tabComplete();
					inputField.setText(reinput);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});

		outText = new JTextArea();
		outText.setEditable(false);
		outText.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane_outPane = new JScrollPane(outText);
		scrollPane_outPane.setViewportBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(scrollPane_outPane, BorderLayout.CENTER);

		logText = new JTextArea();
		logText.setEditable(false);
		logText.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane_logText = new JScrollPane(logText);
		tabbedPane.addTab("Log", null, scrollPane_logText, "Shows a dynamic log of all functions run.");

		final FileDialog fileDialog = new FileDialog(frame);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenConfig = new JMenuItem("Open Configuration Folder");
		mntmOpenConfig.addActionListener(arg0 -> {
			printLoopback("File>Open Configuration Folder");
			FileCommands.openConfig();
		});
		mnFile.add(mntmOpenConfig);

		JMenuItem mntmDeleteConfig = new JMenuItem("Delete Configuration");
		mntmDeleteConfig.addActionListener(e -> {
			printLoopback("File>Delete Configuration");
			FileCommands.deleteConfig(false);
		});
		mnFile.add(mntmDeleteConfig);

		JMenuItem mntmDeleteUtilitiesProFolder = new JMenuItem("Delete Utilities Pro Folder");
		mntmDeleteUtilitiesProFolder.addActionListener(e -> {
			printLoopback("File>Delete Configuration Folder");
			FileCommands.deleteConfig(true);
		});
		mnFile.add(mntmDeleteUtilitiesProFolder);

		JMenuItem mntmChangeConfiguration = new JMenuItem("Change Configuration");
		mntmChangeConfiguration.addActionListener(arg0 -> {
			// TODO FileCommands.configHandler();
		});
		mnFile.add(mntmChangeConfiguration);

		mnFile.addSeparator();

		JMenuItem mntmExportConsole = new JMenuItem("Export Console\n");
		mntmExportConsole.addActionListener(e -> {
			printLoopback("File>Export Utilities_Pro");
			FileCommands.export(1);
		});
		mnFile.add(mntmExportConsole);

		JMenuItem mntmConsoleLog = new JMenuItem("Export Log");
		mntmConsoleLog.addActionListener(e -> {
			printLoopback("File>Export Log");
			FileCommands.export(2);
		});
		mnFile.add(mntmConsoleLog);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Cut");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.META_MASK));
		mnEdit.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Copy");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.META_MASK));
		mnEdit.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.META_MASK));
		mnEdit.add(mntmPaste);

		mnEdit.addSeparator();

		JMenuItem mntmClearConsole = new JMenuItem("Clear Console");
		mntmClearConsole.addActionListener(e -> {
			// There is no need for command log here.
			EditCommands.consoleClear();
		});
		mnEdit.add(mntmClearConsole);

		JMenuItem mntmClearLog = new JMenuItem("Clear Log");
		mntmClearLog.addActionListener(e -> {
			// There is no need for command log here.
			EditCommands.logClear();
		});
		mnEdit.add(mntmClearLog);

		JMenu mnScripts = new JMenu("Scripts");
		menuBar.add(mnScripts);

		JMenuItem mntmPurge = new JMenuItem("Purge Memory");
		mntmPurge.addActionListener(e -> {
			printLoopback("Scripts>Purge Memory");
			ScriptCommands.purge();
		});
		mnScripts.add(mntmPurge);

		JMenuItem mntmRestartAirport = new JMenuItem("Restart Airport");
		mntmRestartAirport.addActionListener(e -> {
			printLoopback("Scripts>Restart Airport");
			ScriptCommands.wireless();
		});
		mnScripts.add(mntmRestartAirport);

		JMenuItem mntmFinderChange = new JMenuItem("Change Finder Options");
		mntmFinderChange.addActionListener(arg0 -> {
			printLoopback("Scripts>Change Finder Options");

			Object[] options = { "Cancel", "Hidden", "Visible" };
			int n = JOptionPane.showOptionDialog(frame,
					"Select an option to change the visibility of the Finder Quit opton and hidden files to.",
					"Utilities Pro OptionPane", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			System.out.println(n);

			if (n == 2) { // Visible
				ScriptCommands.finderConfig(true);
			} else if (n == 1) { // Hidden
				ScriptCommands.finderConfig(false);
			}
			// else cancel
		});
		mnScripts.add(mntmFinderChange);

		mnScripts.addSeparator();

		JMenuItem mntmLoadAndExecute = new JMenuItem("Load and Execute Script");
		mntmLoadAndExecute.addActionListener(arg0 -> {
			printLoopback("Scripts>Load and Exec Script");
			fileDialog.setVisible(true);
			File selScript = new File(fileDialog.getDirectory() + fileDialog.getFile());
			try {
				ExecEngine.scriptEngine(selScript.getCanonicalPath());
			} catch (IOException e) {
				out("Script Load Failed.");
				log("Script Load Failed for: " + selScript);
			}
		});
		mnScripts.add(mntmLoadAndExecute);

		JMenu mnCommand = new JMenu("Command");
		menuBar.add(mnCommand);

		JMenuItem mntmTerminateUtilitiesPro = new JMenuItem("Terminate Utilities Pro Process");
		mntmTerminateUtilitiesPro.addActionListener(e -> {
			printLoopback("Command>Terminate Utilities Pro Process");
			CommandCommands.terminateUtility();
		});
		mntmTerminateUtilitiesPro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.META_MASK));
		mnCommand.add(mntmTerminateUtilitiesPro);

		JMenuItem mntmTerminateArbitraryProcess = new JMenuItem("Terminate Arbitrary Process");
		mntmTerminateArbitraryProcess.addActionListener(e -> {
			printLoopback("Command>Terminate Arbitrary Process");
			CommandCommands.terminateChoose();
		});
		mnCommand.add(mntmTerminateArbitraryProcess);

		mnCommand.addSeparator();

		JMenuItem mntmBombard = new JMenuItem("Bombard");
		mntmBombard.addActionListener(e -> {
			printLoopback("Command>Bombard");
			CommandCommands.bombard();
		});
		mnCommand.add(mntmBombard);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(e -> {
			printLoopback("Help>About");
			HelpCommands.about();
		});
		mnHelp.add(mntmAbout);

		JMenuItem mntmHelp = new JMenuItem("Utilities Pro Help");
		mntmHelp.addActionListener(e -> {
			printLoopback("Help>Utilities Pro Help");
			HelpCommands.helpList();
		});
		mnHelp.add(mntmHelp);

		JMenuItem mntmBashHelp = new JMenuItem("Bash Help");
		mntmBashHelp.addActionListener(e -> {
			printLoopback("Help>Bash Help");
			HelpCommands.bashHelp();
		});
		mnHelp.add(mntmBashHelp);

		mnHelp.addSeparator();

		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.addActionListener(e -> {
			printLoopback("Help>Update");
			HelpCommands.update();
		});
		mnHelp.add(mntmUpdate);

		String greet = String.format("Welcome to Utilities Pro %s '%s'\n===========", version, keyword);
		outText.append(greet);
	}

	static void addToHistory(String input) {
		history.add(input);
		recall = history.size();
	}
}
