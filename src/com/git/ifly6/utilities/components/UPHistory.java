package com.git.ifly6.utilities.components;

import java.util.ArrayList;
import java.util.List;

public class UPHistory {

    public static final int BACK = -1;
    public static final int FORWARD = 1;

    private List<String> history;
    private int pointer;

    public UPHistory() {
        history = new ArrayList<>();
    }

    public void add(String s) {
        history.add(s);
        pointer = history.size() - 1;
    }

    public String step(int i) {
        try {
            int p = pointer - i;
            pointer = p;
            return history.get(p);
        } catch (IndexOutOfBoundsException e) {
            pointer = 0;
            return "";
        }
    }

    public int getPointer() {
        return pointer;
    }

}
