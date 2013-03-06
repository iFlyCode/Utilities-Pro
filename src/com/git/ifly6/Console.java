package com.git.ifly6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Console {

	private JFrame frm_iUtilities;

	// Data
	public static String version = "3.0_alpha";
	public String keyword = "iceland";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// Mac Properties
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "iUtilities " + version);

		// Graphics Calling
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console window = new Console();
					window.frm_iUtilities.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Listener Creation
		Listener.listenerCreate();
	}

	/**
	 * Create the application.
	 */
	public Console() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frm_iUtilities = new JFrame();
		frm_iUtilities.setTitle("iUtilities " + version);
		frm_iUtilities.setBounds(0, 15, 600, 700);
		frm_iUtilities.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frm_iUtilities.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenu mnFileNew = new JMenu("New");
		mnFile.add(mnFileNew);

		JMenuItem menuItem = new JMenuItem("Output File");
		mnFileNew.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Log File");
		mnFileNew.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Download Mindterm");
		mnFile.add(menuItem_2);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem menuItem_quit = new JMenuItem("Quit");
		mnFile.add(menuItem_quit);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem menuItem_log = new JMenuItem("Show Log");
		mnView.add(menuItem_log);

		JMenuItem menuItem_showDIR = new JMenuItem("Show iUtilities Folder");
		mnView.add(menuItem_showDIR);

		JMenuItem menuItem_showDIRworking = new JMenuItem("Show Working DIR");
		mnView.add(menuItem_showDIRworking);

		JSeparator separator1 = new JSeparator();
		mnView.add(separator1);

		JMenuItem menuItem_defaults = new JMenuItem("Reset Defaults");
		mnView.add(menuItem_defaults);

		JMenu menu_LookFeel = new JMenu("Look and Feel");
		mnView.add(menu_LookFeel);

		JMenuItem menuItem_LookFeel_system = new JMenuItem("System");
		menu_LookFeel.add(menuItem_LookFeel_system);

		JMenuItem menuItem_LookFeel_Metal = new JMenuItem("Metal");
		menu_LookFeel.add(menuItem_LookFeel_Metal);

		JMenuItem menuItem_LookFeel_Nimbus = new JMenuItem("Nimbus");
		menu_LookFeel.add(menuItem_LookFeel_Nimbus);

		JMenuItem menuItem_LookFeel_Motif = new JMenuItem("Motif");
		menu_LookFeel.add(menuItem_LookFeel_Motif);

		JMenu mnCommands = new JMenu("Commands");
		menuBar.add(mnCommands);

		JMenuItem menuItem_SysInfo = new JMenuItem("System Information");
		mnCommands.add(menuItem_SysInfo);

		JMenuItem menuItem_ifConfig = new JMenuItem("Interface Configuration");
		mnCommands.add(menuItem_ifConfig);

		JSeparator separator_2 = new JSeparator();
		mnCommands.add(separator_2);

		JMenuItem menuItem_AirportToggle = new JMenuItem("Airport Toggle");
		mnCommands.add(menuItem_AirportToggle);

		JMenuItem menuItem_termRun = new JMenuItem("Terminate Running Process");
		mnCommands.add(menuItem_termRun);

		JMenuItem menuItem_termArbritrary = new JMenuItem("Terminate Process...");
		mnCommands.add(menuItem_termArbritrary);

		JSeparator separator_3 = new JSeparator();
		mnCommands.add(separator_3);

		JMenuItem menuItem_termFrivolous = new JMenuItem("Terminate Frivolous Processes");
		mnCommands.add(menuItem_termFrivolous);

		JMenuItem menuItem_purge = new JMenuItem("Purge");
		mnCommands.add(menuItem_purge);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem menuItem_iUtilitiesHelp = new JMenuItem("iUtilities Help");
		mnHelp.add(menuItem_iUtilitiesHelp);

		JMenuItem menuItem_Acknowledgements = new JMenuItem("Acknowledgements");
		mnHelp.add(menuItem_Acknowledgements);

		JSeparator separator_4 = new JSeparator();
		mnHelp.add(separator_4);

		JMenuItem menuItem_Changelog = new JMenuItem("Changelog");
		mnHelp.add(menuItem_Changelog);

		JMenuItem menuItem_Updates = new JMenuItem("Updates");
		mnHelp.add(menuItem_Updates);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frm_iUtilities.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel consolePane = new JPanel();
		tabbedPane.addTab("Console", null, consolePane, null);
		consolePane.setLayout(new BorderLayout(0, 0));

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		consolePane.add(scrollPane);

		JTextField consoleTextField = new JTextField();
		consoleTextField.setFont(new Font("Monaco", Font.PLAIN, 11));
		consoleTextField.setToolTipText("Type Commands Here");
		consolePane.add(consoleTextField, BorderLayout.SOUTH);

		JPanel loggingPane = new JPanel();
		tabbedPane.addTab("Log\n", null, loggingPane, null);
		loggingPane.setLayout(new BorderLayout(0, 0));

		JTextArea logTextArea = new JTextArea();
		logTextArea.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane_1 = new JScrollPane(logTextArea);
		loggingPane.add(scrollPane_1, BorderLayout.CENTER);

		actionListeners();
	}
	private void actionListeners(){

	}
}
