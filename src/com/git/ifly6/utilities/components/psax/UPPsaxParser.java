package com.git.ifly6.utilities.components.psax;

import com.git.ifly6.iflyLibrary.IflyStrings;
import com.git.ifly6.utilities.UPExecutor;
import com.git.ifly6.utilities.UPInteractable;
import com.git.ifly6.utilities.components.UPLoadingException;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UPPsaxParser implements UPInteractable {

    private List<String> psaxOutput = new ArrayList<>();

    public UPPsaxParser() {
    }

    public void constructTable() {
        UPExecutor e = UPExecutor.getInstance();
        e.execute("ps ax", this);

        // "  PID   TT  STAT      TIME COMMAND";
        if (psaxOutput.isEmpty()) throw new UPLoadingException("Failed to load process data");

        String columns = psaxOutput.get(0);
        // todo write parsing code for ps ax table, selecting PID and COMMAND

        throw new UnsupportedOperationException("ps ax parsing is not complete");
    }

    @Override
    public void out(String s) {
        if (!IflyStrings.isEmpty(s)) psaxOutput.add(s);
    }

    @Override
    public void log(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeDirectory(String p) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getDirectory() {
        return Paths.get(".").toFile();
    }
}
