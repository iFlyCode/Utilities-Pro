package com.git.ifly6.utilities.components;

import com.git.ifly6.utilities.UPInteractable;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UPLinks {

    public static final URI HELP;
    public static final URI GITHUB;
    public static final URI BASH;
    public static final URI README;

    static {
        try {
            HELP = new URI("");
            GITHUB = new URI("");
            BASH = new URI("");
            README = new URI("");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("exceptions in loading pre-formatted URIs should never happen");
        }
    }

    private UPLinks() {
    }

    public static void openLink(URI link, UPInteractable i) {
        try {
            i.out(String.format("Opening URL: %s", link.toASCIIString()));
            Desktop.getDesktop().browse(link);
        } catch (IOException e) {
            e.printStackTrace();
            i.out("Check your internet connection.\n" +
                    "Could not open link: " + i.toString());
        }
    }

}
