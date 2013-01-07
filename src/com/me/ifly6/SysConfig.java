package com.me.ifly6;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SysConfig {

	private JFrame frame;

	public static void sysConfig(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					SysConfig window = new SysConfig();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SysConfig() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JLabel configLabel = new JLabel("System Configuration");
		frame.getContentPane().add(configLabel);
		
		JCheckBox chkboxWireless = new JCheckBox("Wireless Interface");
		frame.getContentPane().add(chkboxWireless);
		
		JCheckBox chkboxCSAkill = new JCheckBox("Attempt to kill CSA");
		frame.getContentPane().add(chkboxCSAkill);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		frame.getContentPane().add(chckbxNewCheckBox_2);
	}

}
