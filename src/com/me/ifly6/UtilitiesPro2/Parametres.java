package com.me.ifly6.UtilitiesPro2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This is the launching class for the programme. It contains information
 * relevant to the programme. Console.class should extend this class. The class
 * launches after setting parameters which are used proramme-wide inside as data
 * or as relevant data to operation.
 * 
 * @since 2.0
 * @author ifly6
 * @deprecated
 */
@Deprecated
public class Parametres {
	// Name: Information (Holds Shared Information)

	// Version-based.
	public static String keyword = "greenhill";
	public static String version = "2.3_01";

	// Strings
	public static String computername = "Unknown";
	public static String copyright = "(c) ifly6@me.com";
	protected static final String starter = "== Utilities Pro Console "
			+ version + " == " + "\nHello " + System.getProperty("user.name")
			+ "!" + "\nBy using this programme, you accept the Licence."
			+ "\nType '/help' for help.";

	// Configuration Files
	public static String userName = System.getProperty("user.name");
	public static final String UtilitiesPro_DIR = "/Users/" + userName
			+ "/Library/Application Support/Utilities Pro";

	/*
	 * System is: <type> <major>.<minor>_<revision>.<sub-revision> For example:
	 * alpha 2.2_01.2 = Major Version 2, Minor Version 2, Revision 01,
	 * Sub-Revision 2 For example: 2.2 = Release, Major Version 2, Minor Version
	 * 2, No revisions.
	 * 
	 * The 2.x Versions: 2.0 = greentree 2.1 = greenwell 2.2 = greenmont 2.3 =
	 * greenhill 2.4 = greenfield 2.5 = greenfall 2.6 = greenpool 2.7 =
	 * greenberg 2.8 = greenland
	 * 
	 * The 3.x Versions 3.0 = iceland 3.1 = iceberg 3.2 = icepool 3.3 = skyfall
	 * 3.4 = icefield 3.5 = everest 3.6 = icemont 3.7 = icewell 3.8 = icedtea
	 */

	// Sets Pre-Launch Parameters and then Invokes the first Class.
	public static void main(String[] args) throws InterruptedException,
			IOException {
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name",
				"Utilities Pro");

		// Get GUI Settings
		FileReader configRead = null;
		String look = "Default";
		try {
			configRead = new FileReader(UtilitiesPro_DIR + "/config");
			Scanner scan = new Scanner(configRead);
			look = scan.nextLine();
		} catch (FileNotFoundException e1) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		}

		// GUI Look and Feel
		if (look.equals("CrossPlatformLAF")) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			} catch (UnsupportedLookAndFeelException e) {
			}
		}

		Console.launchGUI();
	}
}