package com.git.ifly6.utilities.components;

import com.git.ifly6.iflyLibrary.IflySleep;
import com.git.ifly6.utilities.UPExecutor;
import com.git.ifly6.utilities.UPInteractable;
import com.git.ifly6.utilities.UPWindow;

import java.util.Arrays;
import java.util.logging.Logger;

public class UPScripts {

    private static final Logger LOGGER = Logger.getLogger(UPScripts.class.getName());

    private UPScripts() {
    }

    /**
     * Writes show and quit properties for Finder.
     * @param status true if showing hidden and quit menu, false otherwise
     * @param printer      to return output for executor
     * @since 3.3_dev03
     */
    public static void configureFinder(boolean status, UPInteractable printer) {
        String s = String.valueOf(status);
        UPExecutor e = UPExecutor.getInstance();
        e.execute("defaults write com.apple.Finder AppleShowAllFiles " + s, printer);
        e.execute("defaults write com.apple.Finder QuitMenuItem " + s, printer);
        e.execute("killall Finder", printer);
        printer.out("Set showing hidden files and quit menu item to " + s);
    }

    /**
     * Turns AirPort off, then 500 milliseconds later, turns AirPort on.
     * @param printer to return output for executor
     * @since iUtilities v1.0
     */
    public static void restartWireless(UPInteractable printer) {
        UPExecutor e = UPExecutor.getInstance();
        for (String s : Arrays.asList("off", "on")) {
            String command = String.format("networksetup -setairportpower en0 %s", s);
            e.execute(command, printer);
            printer.out("Turned AirPort " + s);
            IflySleep.sleepFor(500);
        }
    }

    public static void stopDockDelay(UPInteractable printer) {
        printer.out("Eliminating Dock auto-hide delay");
        UPExecutor.getInstance().execute("defaults write com.apple.dock autohide-delay -float 0", printer);
    }

    /**
     * Kills process given by selected PID
     * @param pid to kill
     * @param printer for printing
     */
    public static void killProcess(int pid, UPWindow printer) {
        printer.out(String.format("Invoking kill command on process %d", pid));
        UPExecutor.getInstance().execute(String.format("kill %d", pid), printer);
    }
}
