package Final;

import java.awt.event.*;
import javax.swing.*;

private class WindowListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        if (Main.dataNotSaved) {
            int response = JOptionPane.showConfirmDialog(
                null,
                "You have unsaved changes. Would you like to save before exiting?",
                "Unsaved Changes",
                JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                Main.WriteFile();
                System.exit(0);
            } else if (response == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0); // No unsaved changes, exit directly
        }
    }
}