package com.git.ifly6.utilities;

import com.git.ifly6.utilities.components.UPDirectoryManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

public class UPExecutor {

    private static final Logger LOGGER = Logger.getLogger(UPExecutor.class.getName());
    private static UPExecutor instance = null;
    private static Thread executionThread;

    private UPExecutor() {
    }

    public static UPExecutor getInstance() {
        if (instance == null) instance = new UPExecutor();
        return instance;
    }

    public void execute(String s, UPInteractable p) {

        String[] input = Arrays.stream(s.trim().split("(?<!\\\\)\\s+?"))
                .map(UPDirectoryManager::unescapeSpaces)
                .toArray(String[]::new);
        LOGGER.info("command parsed as: " + Arrays.toString(input));

        if (input[0].toLowerCase().equals("cd")) {
            if (input.length == 2) p.changeDirectory(input[1]);
            else LOGGER.info("Attempted to change directory but wrong number of inputs!");

        } else {  // not changing directories
            Runnable runner = () -> {
                try {
                    // ProcessBuilder
                    ProcessBuilder builder = new ProcessBuilder(input);
                    builder.redirectErrorStream(true);
                    builder.directory(p.getDirectory());

                    // Output Stream
                    Scanner scan = new Scanner(new InputStreamReader(builder.start().getInputStream()));
                    while (scan.hasNextLine())
                        p.out(scan.nextLine());

                    scan.close();

                } catch (IOException e) { // Must distinguish between 'Invalid Commands' and
                    // 'Running Failed'
                    p.out("Invalid Command");
                }
            };

            executionThread = new Thread(runner);
            executionThread.start();
        }

        p.out("\n"); // ending
    }

    public void terminateRunning() {
        if (Objects.nonNull(executionThread))
            executionThread.interrupt();
    }

}
