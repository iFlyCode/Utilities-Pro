package com.git.ifly6.utilities.components;

import com.git.ifly6.utilities.UPExecutor;
import com.git.ifly6.utilities.UPInteractable;

public class UPScripts {
    private UPScripts() {
    }

    /**
     * Writes show and quit properties for Finder.
     * @since 3.3_dev03
     * @param status true if showing hidden and quit menu, false otherwise
     * @param i to return input for executor
     */
    public static void configureFinder(boolean status, UPInteractable i) {
        String s = String.valueOf(status);
        UPExecutor e = UPExecutor.getInstance();
        e.execute("defaults write com.apple.Finder AppleShowAllFiles " + s, i);
        e.execute("defaults write com.apple.Finder QuitMenuItem " + s, i);
        e.execute("killall Finder", i);
    }
}
