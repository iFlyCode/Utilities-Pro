package com.git.ifly6.utilities;

import com.git.ifly6.UtilitiesPro3.UtilitiesPro;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class UPExecutor {

    private static UPExecutor instance = null;

    private UPExecutor() {
    }

    public static UPExecutor getInstance() {
        if (instance == null) instance = new UPExecutor();
        return instance;
    }

    public void execute(String s, UPPrintable p) {
        String[] input = s.trim().split("\\s");
        Runnable runner = () -> {
            try {

                // ProcessBuilder
                ProcessBuilder builder = new ProcessBuilder(input);
                builder.redirectErrorStream(true);
                builder.directory(new File(UtilitiesPro.currentDir));
                Process process = builder.start();


                // Output Stream
                InputStream outStream = process.getInputStream();
                InputStreamReader outRead = new InputStreamReader(outStream);
                Scanner scan = new Scanner(outRead);
                while (scan.hasNextLine()) {
                    p.out(scan.nextLine());
                }
                scan.close();

            } catch (IOException e) { // Must distinguish between 'Invalid Commands' and
                // 'Running Failed'
                p.out("Invalid Command");
                p.log("Running Failed or Invalid Command");
            }
        };
        new Thread(runner).start();
    }
}
