package com.git.ifly6.utilities.components;

import java.util.ArrayList;
import java.util.List;

public class UPHistory {

    public static final UPHistoryStep BACK = UPHistoryStep.BACKWARD;
    public static final UPHistoryStep FORWARD = UPHistoryStep.FORWARD;

    private List<String> history;
    private int pointer;

    public UPHistory() {
        history = new ArrayList<>();
    }

    /**
     * Adds new strings to the history. Note that this sets the pointer also at the end.
     * @param s to add to history cache
     */
    public void add(String s) {
        history.add(s);
        pointer = history.size() - 1;
    }

    /**
     * @param step to move <code>pointer</code> forward or back
     * @return string at that pointer variable
     */
    public String step(UPHistoryStep step) {
        // constrain pointer
        pointer = Math.max(
                Math.min(pointer + step.getValue(), history.size() - 1),
                0
        );
        return history.get(pointer);
    }

    public int getPointer() {
        return pointer;
    }

}

enum UPHistoryStep {

    FORWARD(1), BACKWARD(-1);

    private final int value;

    UPHistoryStep(final int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
