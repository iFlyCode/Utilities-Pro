package com.git.ifly6.utilities.components;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UPDirectoryManager {

    private Path path;

    public UPDirectoryManager() {
        path = Paths.get(System.getProperty("user.dir"));
    }

    public Path getPath() {
        return path;
    }

    public void appendPath(String s) {
        s = s.replace("~", System.getProperty("user.home")); // implement tilde
        s = unescapeSpaces(s);

        Path test = Paths.get(s);
        if (test.isAbsolute())
            this.path = test; // if path is absolute, we're done

        else {
            // if not absolute, resolve from current location
            Path newPath = path.resolve(s);
            if (Files.isDirectory(newPath))
                this.path = newPath.normalize();
            else
                throw new UPNotDirectoryException(
                        String.format("Path '%s' is not a directory", newPath.toString())
                );
        }
    }

    public static String unescapeSpaces(String s) {
        return s.replace("\\ ", " ");
    }

    public static String escapeSpaces(String s) {
        return s.replace(" ", "\\ "); // unescape character
    }

}

