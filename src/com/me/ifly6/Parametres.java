package com.me.ifly6;

import java.net.UnknownHostException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Parametres {
	// Name: Information (Holds Shared Information)
	
	public static String computername = "Unknown";
	public static String copyright = "(c) ifly6@me.com - Now Open Source.";
	
	// Version-based.
	public static String password = "greenmont";
	public static String api_version = "0.0.1";
	public static String version = "2.3_dev3Stable";
	
	/*
	 * System is: <type> <major>.<minor>_<revision>.<sub-revision>
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
	 * 2.8 = green-2^3
	 */
	
	public static void main(String[] args) throws UnknownHostException, InterruptedException{
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "iUtilities");

		// GUI Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) { 
		} catch (UnsupportedLookAndFeelException e) {}
		
		Console.launchGUI();
	}
}