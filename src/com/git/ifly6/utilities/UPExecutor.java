package com.git.ifly6.utilities;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

import static com.git.ifly6.utilities.UtilitiesPro.COMPUTER_NAME;

public class UPExecutor {

    private static final Logger LOGGER = Logger.getLogger(UPExecutor.class.getName());
    private static UPExecutor instance = null;

    private UPExecutor() {
    }

    public static UPExecutor getInstance() {
        if (instance == null) instance = new UPExecutor();
        return instance;
    }

    public void execute(String s, UPInteractable p) {
        p.out(String.format("%s %s $ %s", COMPUTER_NAME, p.getDirectory().getName(), s));

        String[] input = s.trim().split("(?<!\\\\)\\s+?");
        LOGGER.info("command parsed as: " + Arrays.toString(input));

        if (input[0].toLowerCase().equals("cd")) {
            if (input.length == 2) p.changeDirectory(input[1]);
            else LOGGER.info("Attempted to change directory but wrong number of inputs!\n");

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
                    p.out("\n"); // ending

                } catch (IOException e) { // Must distinguish between 'Invalid Commands' and
                    // 'Running Failed'
                    p.out("Invalid Command");
                    p.log("Running Failed or Invalid Command");
                }
            };

            new Thread(runner).start();
        }
    }
}
