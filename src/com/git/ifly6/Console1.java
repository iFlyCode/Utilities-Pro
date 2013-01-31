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

public class Console1 {

	private JFrame frm_iUtilities;
	private JTextField textField;

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
					Console1 window = new Console1();
					window.frm_iUtilities.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Console1() {
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

		JMenuItem menuItem_14 = new JMenuItem("Airport Toggle");
		mnCommands.add(menuItem_14);

		JMenuItem menuItem_15 = new JMenuItem("Terminate Running Process");
		mnCommands.add(menuItem_15);

		JMenuItem menuItem_16 = new JMenuItem("Terminate Process...");
		mnCommands.add(menuItem_16);

		JSeparator separator_3 = new JSeparator();
		mnCommands.add(separator_3);

		JMenuItem menuItem_17 = new JMenuItem("Terminate Frivilous Processes");
		mnCommands.add(menuItem_17);

		JMenuItem menuItem_18 = new JMenuItem("Purge");
		mnCommands.add(menuItem_18);

		JMenu menu_4 = new JMenu("Help");
		menuBar.add(menu_4);

		JMenuItem menuItem_19 = new JMenuItem("iUtilities Help");
		menu_4.add(menuItem_19);

		JMenuItem menuItem_20 = new JMenuItem("Acknowledgements");
		menu_4.add(menuItem_20);

		JSeparator separator_4 = new JSeparator();
		menu_4.add(separator_4);

		JMenuItem menuItem_21 = new JMenuItem("Changelog");
		menu_4.add(menuItem_21);

		JMenuItem menuItem_22 = new JMenuItem("Updates");
		menu_4.add(menuItem_22);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frm_iUtilities.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Console", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane);

		textField = new JTextField();
		textField.setFont(new Font("Monaco", Font.PLAIN, 11));
		textField.setToolTipText("Type Commands Here");
		panel.add(textField, BorderLayout.SOUTH);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Log\n", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Monaco", Font.PLAIN, 11));
		JScrollPane scrollPane_1 = new JScrollPane(textArea_1);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
	}

}
