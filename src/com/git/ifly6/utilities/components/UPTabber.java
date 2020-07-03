package com.git.ifly6.utilities.components;

import com.git.ifly6.utilities.UPInteractable;
import com.git.ifly6.utilities.UPWindow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UPTabber {

    private static final Logger LOGGER = Logger.getLogger(UPTabber.class.getName());

    public static final String HOME_FOLDER = System.getProperty("user.home");
    public static final String MULTIPLE_MATCHES = "Multiple matches: ";

    /**
     * @param text             from <code>JTextField</code> to be completing
     * @param workingDirectory which were are already dealing with
     * @return completed string, or if no results, input
     */
    public static String tabComplete(String text, Path workingDirectory, UPInteractable interactable) {

        if (text.startsWith("/"))
            return Arrays.stream(UPWindow.UPInternalCommand.values())
                    .map(UPWindow.UPInternalCommand::toString)
                    .filter(c -> c.startsWith(text.substring(1)))
                    .findFirst()
                    .map(s -> "/" + s)
                    .orElse(text);

        String[] items = text.split("(?<!\\\\)\\s+?");
        String toComplete = items[items.length - 1]; // last item

        if (toComplete.startsWith("~"))
            toComplete = toComplete.replace("~", HOME_FOLDER);

        Path location = Paths.get(toComplete);
        Path absolute = location.isAbsolute()
                ? location
                : workingDirectory.resolve(location);

        List<Path> possiblePaths = complete(absolute, workingDirectory);

        if (possiblePaths.size() == 1)
            return String.join(" ",
                    Arrays.asList(
                            items[0],
                            UPDirectoryManager.escapeSpaces(possiblePaths.get(0).toString())
                    )
            );

        if (possiblePaths.size() > 1) {
            interactable.out(
                    MULTIPLE_MATCHES + "\n\n"
                            + possiblePaths.stream().map(s -> " * " + s).collect(Collectors.joining("\n"))
            );
            throw new UnsupportedOperationException("Multiple possible paths");
        }

        throw new UnsupportedOperationException("No tab-completion matches");
    }

    /**
     * @param input to expand over
     * @return completed path, or if none, input
     */
    private static List<Path> complete(Path input, Path workingDirectory) {
        String ending = input.getFileName().toString();
        Path folder = input.getParent().resolve(".");
        try {
            return Files.list(folder)
                    .map(Path::getFileName) // get file name
                    .map(Path::toString) // turn to strings
                    .filter(s -> ignoreCaseStartsWith(s, ending))
                    .map(s ->
                            workingDirectory.relativize(folder.resolve(s).normalize())
                    ) // if present, map this over
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.info("Failed to expand input: " + input.toString());
            return Collections.singletonList(input);
        }
    }

    /**
     * @param s      to check
     * @param needle that check to start
     * @return true if s.startsWith(needle), but ignoring case
     */
    private static boolean ignoreCaseStartsWith(String s, String needle) {
        return s.toLowerCase().startsWith(needle.toLowerCase());
    }
}
