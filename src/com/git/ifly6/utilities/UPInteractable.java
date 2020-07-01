package com.git.ifly6.utilities;

import java.io.File;

public interface UPInteractable {

    void out(String s);

    void log(String s);

    void changeDirectory(String p);

    File getDirectory();

}
