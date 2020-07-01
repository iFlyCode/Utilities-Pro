package com.git.ifly6.utilities;

import com.git.ifly6.iflyLibrary.IflyDates;
import com.git.ifly6.iflyLibrary.IflySystem;
import com.git.ifly6.iflyLibrary.IflyVersion;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UtilitiesPro {

    private static final Logger LOGGER = Logger.getLogger(UtilitiesPro.class.getName());
    private static final IflyVersion VERSION = new IflyVersion(4, 0, 1).setName("Utilities Pro");
    private static FileHandler loggerFileHandler = null; // Save logs to file, if null... uh stuff

    public static final Path APP_SUPPORT;

    static {
        // Do this static initialisation block when LAF is called
        // Find or create the application support directory
        if (IflySystem.IS_OS_WINDOWS) APP_SUPPORT = Paths.get(System.getenv("LOCALAPPDATA"), "Communique");
        else if (IflySystem.IS_OS_MAC) {
            APP_SUPPORT = Paths.get(System.getProperty("user.home"), "Library", "Application Support", "Communique");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", UtilitiesPro.VERSION.toString());

        } else APP_SUPPORT = Paths.get(System.getProperty("user.dir"), "config");

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
            loggerFileHandler = new FileHandler(logFile.toString());
            loggerFileHandler.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(loggerFileHandler);

        } catch (SecurityException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initialise();


    }

    private static void initialise() {
        setLAF();
    }

    private static void setLAF() {
        // Set our look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException lfE) {
            try {
                UIManager.setLookAndFeel(
                        Arrays.stream(UIManager.getInstalledLookAndFeels())
                                .filter(laf -> laf.getName().equals("Nimbus"))
                                .findFirst()
                                .orElseThrow(ClassNotFoundException::new)
                                .getClassName()
                );
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e) {
                LOGGER.severe("Cannot initialise? Cannot find basic Nimbus look and feel.");
                e.printStackTrace();
            }
            lfE.printStackTrace();
        }
    }

}
