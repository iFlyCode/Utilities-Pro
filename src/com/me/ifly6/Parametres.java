package com.me.ifly6;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Parametres {
	// Name: Information (Holds Shared Information)
	
	// Version-based.
	public static String password = "greenmont";
	public static String version = "2.3_dev4Stable";
	
	// Strings
	public static String computername = "Unknown";
	public static String copyright = "(c) ifly6@me.com";
	static final String starter = "== iUtilities Console " + version + " == " + 
			"\nHello " + System.getProperty("user.name") + "!" + 
			"\nType '/help' for help.";

	// Configuration Files
	public static String userName = System.getProperty("user.name");
	public static final String IUTILITIES_DIR = "/Users/" + userName + "/Library/Application Support/iUtilities";

	/* System is: <type> <major>.<minor>_<revision>.<sub-revision>
	 * For example: alpha 2.2_01.2 = Major Version 2, Minor Version 2, Revision 01, Sub-Revision 2
	 * For example: 2.2 = Release, Major Version 2, Minor Version 2, No revisions.
	 * 
	 * The 2.x Versions:
	 * 2.0 = greentree
	 * 2.1 = greenwell
	 * 2.2 = greenmont
	 * 2.3 = greenhill
	 * 2.4 = greenfield
	 * 2.5 = greenfall
	 * 2.6 = greenpool
	 * 2.7 = greenbyte
	 * 2.8 = greenland
	 */

	// Sets Pre-Launch Parameters and then Invokes the first Class.
	public static void main(String[] args) throws InterruptedException, IOException{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "iUtilities");

		// Get GUI Settings
		FileReader configRead = null;
		String look = "Default";
		try {
			configRead = new FileReader(IUTILITIES_DIR + "/config");
			Scanner scan = new Scanner(configRead);
			look = scan.nextLine();
		} catch (FileNotFoundException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		}
		// BufferedReader br = new BufferedReader(configRead);
		// String r = br.readLine();

		// GUI Look and Feel
		if (look.equals("CrossPlatformLAF")){
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		} else {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) { 
			} catch (UnsupportedLookAndFeelException e) {}
		}

		Console.launchGUI();
	}
}