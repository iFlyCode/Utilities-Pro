package com.git.ifly6.utilities.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Logger;

public class UPTabber {

    private static final Logger LOGGER = Logger.getLogger(UPTabber.class.getName());
    public static final String HOME_FOLDER = System.getProperty("user.home");

    /**
     * @param text             from <code>JTextField</code> to be completing
     * @param workingDirectory which were are already dealing with
     * @return completed string, or if no results, input
     */
    public static String tabComplete(String text, Path workingDirectory) {
        String[] items = text.split("(?<!\\\\)\\s+?");
        String toComplete = items[1];

        if (toComplete.startsWith("~"))
            toComplete = toComplete.replace("~", HOME_FOLDER);

        Path location = Paths.get(toComplete);
        Path absolute = location.isAbsolute()
                ? location
                : workingDirectory.resolve(location);
        return String.join(" ",
                Arrays.asList(
                        items[0],
                        complete(absolute, workingDirectory).toString()
                                .replace(" ", "\\ ")
                )
        );

    }

    /**
     * @param input to expand over
     * @return completed path, or if none, input
     */
    private static Path complete(Path input, Path workingDirectory) {
        String ending = input.getFileName().toString();
        Path folder = input.getParent().resolve(".");
        try {
            return Files.list(folder)
                    .map(Path::getFileName) // get file name
                    .map(Path::toString) // turn to strings
                    .filter(s -> ignoreCaseStartsWith(s, ending))
                    .findFirst()
                    .map(s ->
                            workingDirectory.relativize(folder.resolve(s).normalize())
                    ) // if present, map this over
                    .orElse(input); // if not present, return this

        } catch (IOException e) {
            LOGGER.info("Failed to expand input: " + input.toString());
            return input.relativize(input);
        }
    }

    /**
     * @param s to check
     * @param needle that check to start
     * @return true if s.startsWith(needle), but ignoring case
     */
    private static boolean ignoreCaseStartsWith(String s, String needle) {
        return s.toLowerCase().startsWith(needle.toLowerCase());
    }
}
