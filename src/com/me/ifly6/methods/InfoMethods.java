package com.me.ifly6.methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


import com.me.ifly6.ConsoleIf;
import com.me.ifly6.Info;
import com.me.ifly6.TextProc;

public class InfoMethods extends TextProc {
	// Name: Methods for Providing Information About iUtilities
	private static final long serialVersionUID = 1L;

	public static void about(){
		append("== About iUtilities " + Info.version + " ==");
		out(Info.copyright);
		out("Version " + Info.version + " '" + Info.password + "'");
	}

	public static void acknowledgements() throws IOException{
		mkdir();
		// TODO Change Libraries: String[] url = { "curl", "-o", IUTILITIES_DIR + "/acknowledgements.txt", "http://ifly6.no-ip.org/iUtilities/acknowledgements.txt" };
		rt.exec(url);
		String r = "\n";
		// TODO Change: fstream = new FileReader("/Users/" + userName + "/Library/Application Support/iUtilities/acknowledgements.txt");
		BufferedReader br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null){
			ConsoleIf.append(r); }
		br.close();
		log("Acknowledgements Processing Trigger Completed");
	}

	public static void changelog() throws IOException{
		mkdir();
		// TODO Change Libraries: String[] url = { "curl","-o", IUTILITIES_DIR + "/changelog.txt", "http://ifly6.no-ip.org/iUtilities/changelog.txt" };
		rt.exec(url);
		String r = "\n";
		// TODO Change: fstream = new FileReader(IUTILITIES_DIR + "/changelog.txt");
		br = new BufferedReader(fstream);
		r = br.readLine();
		while ((r = br.readLine()) != null) {
			append(r); }
		br.close();
		log("Changelog Processing Trigger Completed");
	}

	public static void info() throws InterruptedException, IOException{
		log("System Readout Invoked.");
		setText(null);
		append("Generated By: " + Info.version + " '" + Info.password + "' \n");
		append(" -- Current Running Processes -- ");
		String[] com = { "pslist", "-x" };
		String[] com1 = { "ipconfig" };
		String[] com2 = { "netstat" };
		Process proc = rt.exec(com);
		Process proc1 = rt.exec(com1);
		Process proc2 = rt.exec(com2);
	
		InputStream stderr = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(stderr);
		Scanner scan = new Scanner(isr);
		while (scan.hasNextLine()) {
			out(scan.nextLine()); }
	
		InputStream stderr1 = proc1.getInputStream();
		InputStreamReader isr1 = new InputStreamReader(stderr1);
		scan = new Scanner(isr1);
		append(" -- Internet Interface Information -- ");
		while (scan.hasNextLine()) {
			out(scan.nextLine()); }
	
		InputStream stderr2 = proc2.getInputStream();
		InputStreamReader isr2 = new InputStreamReader(stderr2);
		scan = new Scanner(isr2);
		append(" -- Processes Information -- ");
		while (scan.hasNextLine()) {
			out(scan.nextLine()); }
	
		// Hardware
		out("Available cores: " + rt.availableProcessors());
		out("Free memory (bytes): " + rt.freeMemory());
		long maxMemory = rt.maxMemory();
		out("Max. memory (Kilobytes): " + maxMemory/1000);
		out("Total memory (Kilobytes): " + (rt.totalMemory()/1000));
		File[] roots = File.listRoots();
		out("");
		for (File root : roots) {
			out("File system root: " + root.getAbsolutePath());
			out("FS Capacity (bytes): " + root.getTotalSpace());
			out("FS Free (bytes): " + root.getFreeSpace());
			out("FS Usable (bytes): " + root.getUsableSpace());
		}
		out("");
		out(System.getProperty("java.runtime.name") + " version " + System.getProperty("java.runtime.version") +
				System.getProperty("java.vm.version") + " by " +
				System.getProperty("java.vm.vendor"));
		out("Execution Directory: " + System.getProperty("user.dir"));
		out("");
		String nameOS = "os.name";
		String versionOS = "os.version";
		out("Operating System: " + System.getProperty(nameOS) + " " + System.getProperty(versionOS));
		out("User: " + System.getProperty("user.name") + " ... with Home at: " + System.getProperty("user.home"));
		out("Desktop: " + System.getProperty("sun.desktop"));
	}

}
