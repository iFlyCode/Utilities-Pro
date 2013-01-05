package com.me.ifly6.Commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.text.DefaultCaret;

import com.me.ifly6.Console;
import com.me.ifly6.ConsoleIf;
import com.me.ifly6.Info;
import com.me.ifly6.TextProc;

public class InfoMethods extends TextProc {
	// Name: Methods for Providing Information About iUtilities
	private static final long serialVersionUID = 1L;

	public static void about(){
		append("== About iUtilities " + Info.version );
		out(Info.copyright);
		out("Version " + Info.version + " '" + Info.password + "'");
	}

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

	public static void acknowledgements() throws IOException{
		mkdir();
		String[] url = { "curl", "-o", IUTILITIES_DIR + "/acknowledgements.txt", "http://ifly6.no-ip.org/iUtilities/acknowledgements.txt" };
		rt.exec(url);
		String r = "\n";
		fstream = new FileReader("/Users/" + userName + "/Library/Application Support/iUtilities/acknowledgements.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null){
			ConsoleIf.append(r); }
		br.close();
		log("Acknowledgements Processing Trigger Completed");
	}

	public static void changelog() throws IOException{
		mkdir();
		String[] url = { "curl","-o", IUTILITIES_DIR + "/changelog.txt", "http://ifly6.no-ip.org/iUtilities/changelog.txt" };
		rt.exec(url);
		String r = "\n";
		fstream = new FileReader(IUTILITIES_DIR + "/changelog.txt");
		br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null) {
			append(r); }
		br.close();
		log("Changelog Processing Trigger Completed");
	}

}
