package com.me.ifly6.UtilitiesPro2;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * This programme is designed to be a scripting system. It was replaced by the
 * seperate programme, iUtilities.
 * 
 * @since 2.2
 * @deprecated Useless, and incapable of working. Project Abandoned.
 */
@Deprecated
public class SysConfig {

	private JFrame frame;

	public static void sysConfig(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
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
		frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

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
