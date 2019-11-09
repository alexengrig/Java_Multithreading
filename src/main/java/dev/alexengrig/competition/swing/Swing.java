package dev.alexengrig.competition.swing;

import javax.swing.*;

public class Swing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame("SwingWorker Demo");
        });
    }
}
