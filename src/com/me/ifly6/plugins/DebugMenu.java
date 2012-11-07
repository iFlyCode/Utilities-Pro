package com.me.ifly6.plugins;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;
import com.me.ifly6.Info;

public class DebugMenu extends com.me.ifly6.API {
	
	private static final long serialVersionUID = 1L;
	JFrame frame1 = new JFrame("iUtilities " + Info.version + " API DebugMenu");
	JPanel pane1 = new JPanel();
	static JTextArea display1 = new JTextArea();
	JScrollPane sp1 = new JScrollPane(display1);

	DebugMenu(){
		frame1.setBounds(50, 50, 670, 735);
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container con = getContentPane();
		getContentPane().setLayout(new BorderLayout());
		con.add(this.pane1);
		pane1.setLayout(new BorderLayout());
		frame1.add(pane1);

		pane1.add(sp1);
		display1.setEditable(false);
		display1.setFont(font);
		frame1.setVisible(true);
	}

	public static void main(String[] args){
		while (true){
		new DebugMenu();
		String tmp = log.getText();
		display1.setText(tmp);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { log("Plugin DebugMen Failed: Interrupted Exception"); }
		}
	}
}
