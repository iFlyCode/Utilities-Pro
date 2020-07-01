package com.git.ifly6.utilities.components;

import com.git.ifly6.utilities.UPInteractable;

public enum UPCommands {

    EXPORT_LOG {
        @Override
        public void execute(UPInteractable i) {

        }
    },

    CLEAR {
        @Override
        public void execute(UPInteractable i) {

        }
    };

    public abstract void execute(UPInteractable i);

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
