package com.git.ifly6.utilities;

import com.bulenkov.darcula.DarculaLaf;
import com.git.ifly6.iflyLibrary.IflyDates;
import com.git.ifly6.iflyLibrary.IflySystem;
import com.git.ifly6.iflyLibrary.IflyVersion;
import com.git.ifly6.iflyLibrary.swing.IflyDialogs;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UtilitiesPro {

    public static final String KEYWORD = "A la volonté du peuple";
    public static final IflyVersion VERSION = new IflyVersion(4, 0, 1).setName("Utilities Pro");
    public static final String FULL_VERSION = String.format("%s '%s'", VERSION.toString(), KEYWORD);

    private static final Logger LOGGER = Logger.getLogger(UtilitiesPro.class.getName());

    public static final Path APP_SUPPORT;
    public static String COMPUTER_NAME;

    static {
        // Do this static initialisation block when LAF is called
        // Find or create the application support directory
        if (IflySystem.IS_OS_MAC) {
            APP_SUPPORT = Paths.get(System.getProperty("user.home"),
                    "Library",
                    "Application Support",
                    "Utilities-Pro");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", UtilitiesPro.VERSION.toString());

        } else APP_SUPPORT = Paths.get(System.getProperty("user.dir"), "app-support");

        // Create the application support directory
        try {
            Files.createDirectories(APP_SUPPORT);
        } catch (IOException e1) {
            e1.printStackTrace();
            LOGGER.warning("Cannot create directory");
        }

        // Get us a reasonable-looking log format
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");

        // Make sure we can also log to file, apply this to the root logger
        try {
            Path logFile = APP_SUPPORT
                    .resolve("log")
                    .resolve(String.format("communique-session-%s.log", IflyDates.getTime()));

            Files.createDirectories(logFile.getParent()); // make directory
            // Save logs to file, if null... uh stuff
            FileHandler loggerFileHandler = new FileHandler(logFile.toString());
            loggerFileHandler.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(loggerFileHandler);

        } catch (SecurityException | IOException | NullPointerException e) {
            e.printStackTrace();
        }

        try {
            String s = InetAddress.getLocalHost().getHostName();
            COMPUTER_NAME = s.substring(0, s.indexOf("."));
        } catch (UnknownHostException e) {
            System.out.println("Failed to get local host-name");
            COMPUTER_NAME = "Unknown";
        }
    }

    public static void main(String[] args) {
        // show warning if not mac
        if (!IflySystem.IS_OS_MAC)
            IflyDialogs.showMessageDialog(null,
                    "Scripts in this program depend on macOS implementation details",
                    "Warning");

        initialise();
        EventQueue.invokeLater(() -> {
            JFrame frame = new JFrame(VERSION.toString());
            frame.setMinimumSize(new Dimension(500, 400));
            frame.setLocationRelativeTo(null); // centre

            UPWindow w = new UPWindow(frame);
            frame.setContentPane(w.panel);
            frame.setJMenuBar(w.createMenus());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }

    private static void initialise() {
        setLAF();
    }

    private static void setLAF() {
        try {
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            LOGGER.info("Failed to set Darcula look and feel");
        }
    }

}
